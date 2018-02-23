package com.heneli.copia.schedule;

import com.heneli.copia.model.Match;
import com.heneli.copia.model.Pickup;
import com.heneli.copia.model.Recipient;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Schedule {

//    @Autowired
//    MatchJdbcRepository matchJdbcRepository;

    private List<Pickup> pickups;
    private List<Recipient> recipients;
    private List<Match> matches;
    private Map<Integer, List<Match>> matchMap;

    public Map<Integer, List<Match>> getMatchMap() {
        return matchMap;
    }

    public List<Match> getMatches(Pickup pickup) {
        return matchMap.get(pickup.getPickupId());
    }

    public Schedule(List<Pickup> pickups, List<Recipient> recipients) {
        this.pickups = pickups;
        this.recipients = recipients;
        this.matches = new ArrayList<>();
//        this.matchMap = new HashMap<>();
        this.matchMap = new ConcurrentHashMap<>();
        generateSchedule(pickups, recipients);
    }

    public void generateSchedule(List<Pickup> pickups, List<Recipient> recipients) {
        Map<Integer, List<Recipient>> qualifiedRecipientMap = new HashMap<>();

        for (Pickup pickup : pickups) {
            qualifiedRecipientMap.put(pickup.getPickupId(), findQualifiedRecipients(pickup, recipients));
        }

        Map<Integer, List<Recipient>> nonSingleMatchMap = new HashMap<>();
        List<Pickup> filteredPickups = new ArrayList<>();

        System.out.print("Generating single recipient matches...");
        for (Pickup pickup : pickups) {
            List<Recipient> rs = qualifiedRecipientMap.get(pickup.getPickupId());

            if (rs == null) { // pickup has no match qualified recipients, thus no matches
                filteredPickups.add(pickup);
                continue;
            }

            List<List<Recipient>> singleDeliveryCandidates = getOneRecipientMatches(pickup, rs);
            List<Recipient> singleMatches = singleDeliveryCandidates.get(0);
            List<Match> singleDeliveryMatches = new ArrayList<>();

            if (singleMatches.size() == 0) {
                filteredPickups.add(pickup);
            } else {
                for (Recipient recipient : singleMatches) {
                    List<Recipient> singleMatch = Arrays.asList(recipient);
                    Match ins = new Match(pickup, singleMatch);
                    singleDeliveryMatches.add(ins);
                }
            }

            nonSingleMatchMap.put(pickup.getPickupId(), singleDeliveryCandidates.get(1));

            if (singleDeliveryCandidates != null) {
                if (singleDeliveryMatches.size() != 0) {
//                    List<Match> updateMatches = matchMap.get(pickup.getPickupId());
//                    updateMatches.addAll(singleDeliveryMatches);

                    matchMap.put(pickup.getPickupId(), singleDeliveryMatches);
                }
            }
        }
        System.out.print("   Done.\n");

        System.out.print("Generating two recipient matches...");
        pickups.parallelStream().forEach((pickup -> {
            List<Match> m = getTwoRecipientMatches(pickup, nonSingleMatchMap.get(pickup.getPickupId()));
            if (m != null) {
                if (m.size() > 0) {
                    List<Match> updateMatches = matchMap.get(pickup.getPickupId());
                    if (updateMatches == null) updateMatches = new ArrayList<>();
                    updateMatches.addAll(m);

                    matchMap.put(pickup.getPickupId(), updateMatches);
                }
            }
        }));
        System.out.print("   Done.\n");

        System.out.print("Generating three recipient matches...");
        pickups.parallelStream().forEach((pickup -> {
            List<Match> m = getThreeRecipientMatches(pickup, nonSingleMatchMap.get(pickup.getPickupId()));
            if (m != null) {
                if (m.size() > 0) {
                    List<Match> updateMatches = matchMap.get(pickup.getPickupId());
                    if (updateMatches == null) updateMatches = new ArrayList<>();
                    updateMatches.addAll(m);

                    matchMap.put(pickup.getPickupId(), updateMatches);
                }
            }
        }));
        System.out.print("   Done.\n");

//        System.out.print("Generating four recipient matches. This may take a couple minutes...");
//        pickups.parallelStream().forEach(pickup -> {
//            List<Match> m = getFourRecipientMatches(pickup, nonSingleMatchMap.get(pickup.getPickupId()));
//            if (m != null) {
//                if (m.size() > 0) {
//                    List<Match> updateMatches = matchMap.get(pickup.getPickupId());
//                    if (updateMatches == null) updateMatches = new ArrayList<>();
//                    updateMatches.addAll(m);
//
//                    matchMap.put(pickup.getPickupId(), updateMatches);
//                }
//            }
//        });
//        System.out.print("   Done.\n");

    }

    public void storeMatches() {
        for (Pickup p : pickups) {
            List<Match> m = matchMap.get(p.getPickupId());
            try {
                for (Match match : m) {
//                    matchJdbcRepository.insert(match);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public void printMatches() {
        for (Pickup p : pickups) {
            List<Match> m = matchMap.get(p.getPickupId());
            if (m != null) {
                for (Match match : m) {
                    System.out.println(match);
                }
            }
        }
    }

    // computes matches for one pickup
    public static List<Recipient> findQualifiedRecipients(Pickup p, List<Recipient> rs) {
        return rs.stream()
                .filter(r -> r.distance(p) <= 5
                        && r.accepts(p)
                        && r.isOpenAt(p.getPickupAt()))
                .collect(Collectors.toList());
    }

//    // sorts matches for one pickup
//    public static List<Recipient> sortMatches(Pickup p, List<Recipient> matches) {
//        matches.sort(Comparator.comparingDouble(o -> o.distance(p)));
//        matches.sort(Comparator.comparingDouble(o -> o.countAccepts(p)));
//        return matches;
//    }

    // TODO - how do i return List<Match> while removing single matches from recipients
    // maybe answer here -> https://stackoverflow.com/questions/30042222/remove-and-collect-elements-with-java-streams
    public List<List<Recipient>> getOneRecipientMatches(Pickup p, List<Recipient> rs) {
        List<Recipient> singleMatch = new ArrayList<>();
        List<Recipient> nonSingleMatch = new ArrayList<>();

        int pV = p.getCategories();

        for (Recipient r : rs) {
            int aV = 63 ^ r.getRestrictions(); //accept vector

            if (BinaryMatchUtil.matchOne(pV, aV)) { // if providing is a subset of restrictions
                singleMatch.add(r);
            } else {
                nonSingleMatch.add(r);
            }
        }

        List<List<Recipient>> matchedAndUnmatched = new ArrayList<>();
        matchedAndUnmatched.add(0, singleMatch);
        matchedAndUnmatched.add(1, nonSingleMatch);

        return matchedAndUnmatched;
    }

    public List<Match> getTwoRecipientMatches(Pickup p, List<Recipient> rs) {
        Set<Match> doubleMatches = new HashSet<>();

        int pV = p.getCategories();

        for (Recipient r1 : rs) {
            int a1V = 63 ^ r1.getRestrictions();
            for (Recipient r2 : rs) {
                if (r1 == r2) continue;
                int a2V = 63 ^ r2.getRestrictions();

                if (BinaryMatchUtil.matchTwo(pV, a1V, a2V)) {
                    List<Recipient> recipientsPair = Arrays.asList(r1, r2);
                    recipientsPair.sort((o1, o2) -> Double.compare(p.distance(o1), p.distance(o2)));

                    Match twoRecipientMatch = new Match(p, recipientsPair);

                    doubleMatches.add(twoRecipientMatch);
                }
            }
        }

        return new ArrayList<>(doubleMatches);
    }

    public List<Match> getThreeRecipientMatches(Pickup p, List<Recipient> rs) {
        Set<Match> tripleMatches = new HashSet<>();

        int pV = p.getCategories();

        for (Recipient r1 : rs) {
            for (Recipient r2 : rs) {
                if (r2 == r1) continue;
                for (Recipient r3 : rs) {
                    if (r3 == r1 || r3 == r2) continue;

                    int a1V = 63 ^ r1.getRestrictions();
                    int a2V = 63 ^ r2.getRestrictions();
                    int a3V = 63 ^ r3.getRestrictions();

                    if (BinaryMatchUtil.matchThree(pV, a1V, a2V, a3V)) {
                        List<Recipient> recipientsTriple = Arrays.asList(r1, r2, r3);
                        recipientsTriple.sort((o1, o2) -> Double.compare(p.distance(o1), p.distance(o2)));

                        Match threeRecipientMatch = new Match(p, recipientsTriple);

                        tripleMatches.add(threeRecipientMatch);
                    }
                }
            }
        }

        return new ArrayList<>(tripleMatches);
    }

    public List<Match> getFourRecipientMatches(Pickup p, List<Recipient> rs) {
        Set<Match> quadMatches = new HashSet<>();

        int pV = p.getCategories();

        for (Recipient r1 : rs) {
            for (Recipient r2 : rs) {
                if (r2 == r1) continue;
                for (Recipient r3 : rs) {
                    if (r3 == r1 || r3 == r2) continue;
                    for (Recipient r4 : rs) {
                        if (r4 == r1 || r4 == r2 || r4 == r3) continue;

                        int a1V = 63 ^ r1.getRestrictions();
                        int a2V = 63 ^ r2.getRestrictions();
                        int a3V = 63 ^ r3.getRestrictions();
                        int a4V = 63 ^ r4.getRestrictions();

                        if (BinaryMatchUtil.matchFour(pV, a1V, a2V, a3V, a4V)) {
                            List<Recipient> recipientsTriple = Arrays.asList(r1, r2, r3, r4);
                            recipientsTriple.sort((o1, o2) -> Double.compare(p.distance(o1), p.distance(o2)));

                            Match fourRecipientMatch = new Match(p, recipientsTriple);

                            quadMatches.add(fourRecipientMatch);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(quadMatches);
    }
}
