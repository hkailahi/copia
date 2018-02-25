package com.heneli.copia.model;

import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

@Data
public class Match {

    @Transient
    private Pickup pickup;
    @Transient
    private List<Recipient> recipientList;

    private long matchId;
    private int pickupId;
    private Integer recipientId1, recipientId2, recipientId3, recipientId4, recipientId5, recipientId6;
    private int deliveries; // number of recipients this match will visit
    private double distance; // for ordering list of most favorable(0) to least favorable(n) match

    public Match(Pickup pickup) {
        this.pickup = pickup;
    }

    public Match(Pickup pickup, List<Recipient> recipientList) {
        this.pickup = pickup;
        this.recipientList = recipientList;

        this.deliveries = recipientList.size();
        this.distance = recipientList.stream()
                                .mapToDouble(r -> r.distance(pickup))
                                .sum();

        this.pickupId = pickup.getPickupId();
        this.recipientId1 = (recipientList.size() > 0) ? recipientList.get(0).getRecipientId() : null;
        this.recipientId2 = (recipientList.size() > 1) ? recipientList.get(1).getRecipientId() : null;
        this.recipientId3 = (recipientList.size() > 2) ? recipientList.get(2).getRecipientId() : null;
        this.recipientId4 = (recipientList.size() > 3) ? recipientList.get(3).getRecipientId() : null;
        this.recipientId5 = (recipientList.size() > 4) ? recipientList.get(4).getRecipientId() : null;
        this.recipientId6 = (recipientList.size() > 5) ? recipientList.get(5).getRecipientId() : null;
    }

    public Match() {}
}
