package com.heneli.copia.schedule;

import com.heneli.copia.model.Pickup;
import com.heneli.copia.model.Recipient;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Schedule {

    private List<Pickup> pickups;
    private List<Recipient> recipients;

    public Schedule(List<Pickup> pickups, List<Recipient> recipients) {
        this.pickups = pickups;
        this.recipients = recipients;
        generateSchedule();
    }

    public void generateSchedule() {
        // for all pickups : find, sort, then store matches with recipients
        // for all pickups : find and store optimal delivery (i.e., sortedMatches[0])

    }

    // computes matches for one pickup
    public static List<Recipient> findMatches(Pickup p, List<Recipient> rs) {
        return rs.stream()
                .filter(r -> r.distance(p) <= 5
                        && r.accepts(p)
                        && r.isOpenAt(p.getPickupAt()))
                .collect(Collectors.toList());
    }

    // sorts matches for one pickup
    public static List<Recipient> sortMatches(Pickup p, List<Recipient> matches) {
        matches.sort(Comparator.comparingDouble(o -> o.distance(p)));
        matches.sort(Comparator.comparingDouble(o -> o.countAccepts(p)));
        return matches;
    }

}
