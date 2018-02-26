package com.heneli.copia.schedule;

import com.heneli.copia.model.Match;
import com.heneli.copia.model.Pickup;
import com.heneli.copia.model.Recipient;
import com.heneli.copia.util.BinMatchers;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Schedule {

    private final Map<Integer, List<Match>> matchMap;

    public List<Match> getMatches(Pickup pickup) {
        return matchMap.get(pickup.getPickupId());
    }

    public Schedule(List<Pickup> pickups, List<Recipient> recipients) {
        this.matchMap = new ConcurrentHashMap<>();
        generateSchedule(pickups, recipients);
    }

    private void generateSchedule(List<Pickup> pickups, List<Recipient> recipients) {
        Map<Integer, List<Recipient>> qualifiedRecipientMap = new HashMap<>();

        for (Pickup pickup : pickups) {
            qualifiedRecipientMap.put(pickup.getPickupId(), findQualifiedRecipients(pickup, recipients));
        }

        pickups.parallelStream().forEach(pickup -> {
            List<Match> m = getAllRecipientMatches(pickup, qualifiedRecipientMap.get(pickup.getPickupId()));
            if (m != null) {
                if (m.size() > 0) {
                    List<Match> updateMatches = matchMap.get(pickup.getPickupId());
                    if (updateMatches == null) updateMatches = new ArrayList<>();
                    updateMatches.addAll(m);

                    matchMap.put(pickup.getPickupId(), updateMatches);
                }
            }
        });
    }

    public static List<Recipient> findQualifiedRecipients(Pickup p, List<Recipient> rs) {
        return rs.stream()
                .filter(r -> r.distance(p) <= 5
                        && r.accepts(p)
                        && r.isOpenAt(p.getPickupAt()))
                .collect(Collectors.toList());
    }

    public List<Match> getAllRecipientMatches(Pickup p, List<Recipient> rs) {
        Set<Match> allMatches = new HashSet<>();

        int pV = p.getCategories();

        for (Recipient r1 : rs) {
            int a1V = 63 ^ r1.getRestrictions();

            if (BinMatchers.isOnlyOneToOneBinMatch(pV, a1V)) { // if providing is a subset of restrictions
                allMatches.add(new Match(p, Arrays.asList(r1)));
                continue;
            }

            for (Recipient r2 : rs) {
                if (r2 == r1) continue;
                int a2V = 63 ^ r2.getRestrictions();

                if (BinMatchers.isOnlyOneToTwoBinMatch(pV, a1V, a2V)) {
                    List<Recipient> recipientsPair = Arrays.asList(r1, r2);
                    recipientsPair.sort(Comparator.comparingDouble(p::distance));

                    Match twoRecipientMatch = new Match(p, recipientsPair);

                    allMatches.add(twoRecipientMatch);
                    continue;
                }

                for (Recipient r3 : rs) {
                    if (r3 == r1 || r3 == r2) continue;
                    int a3V = 63 ^ r3.getRestrictions();

                    if (BinMatchers.isOnlyOneToThreeBinMatch(pV, a1V, a2V, a3V)) {
                        List smallerMatches = BinMatchers.findMatchCombinations(Arrays.asList(a1V, a2V, a3V))
                                .stream()
                                .filter(c -> BinMatchers.isOnlyOneToOneBinMatch(pV, c.get(0)))
                                .filter(c -> BinMatchers.isOnlyOneToTwoBinMatch(pV, c.get(0), c.get(1)))
                                .collect(Collectors.toList());

                        if (smallerMatches.size() > 0) continue;

                        List<Recipient> recipientsTriple = Arrays.asList(r1, r2, r3);
                        recipientsTriple.sort(Comparator.comparingDouble(p::distance));

                        Match threeRecipientMatch = new Match(p, recipientsTriple);

                        allMatches.add(threeRecipientMatch);
                        continue;
                    }

                    for (Recipient r4 : rs) {
                        if (r4 == r1 || r4 == r2 || r4 == r3) continue;
                        int a4V = 63 ^ r4.getRestrictions();


                        if (BinMatchers.isOnlyOneToFourBinMatch(pV, a1V, a2V, a3V, a4V)) {
                            List smallerMatches = BinMatchers.findMatchCombinations(Arrays.asList(a1V, a2V, a3V, a4V))
                                    .stream()
                                    .filter(c -> BinMatchers.isOnlyOneToOneBinMatch(pV, c.get(0)))
                                    .filter(c -> BinMatchers.isOnlyOneToTwoBinMatch(pV, c.get(0), c.get(1)))
                                    .filter(c -> BinMatchers.isOnlyOneToThreeBinMatch(pV, c.get(0), c.get(1), c.get(2)))
                                    .collect(Collectors.toList());

                            if (smallerMatches.size() > 0) continue;

                            List<Recipient> recipientsTriple = Arrays.asList(r1, r2, r3, r4);
                            recipientsTriple.sort(Comparator.comparingDouble(p::distance));

                            Match fourRecipientMatch = new Match(p, recipientsTriple);

                            allMatches.add(fourRecipientMatch);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(allMatches);
    }
}
