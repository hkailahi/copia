package com.heneli.copia.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Entity
public class Recipient extends User {

    @Id
    private int recipientId;
    private int restrictions;
    private int Sunday;
    private int Monday;
    private int Tuesday;
    private int Wednesday;
    private int Thursday;
    private int Friday;
    private int Saturday;

    public boolean accepts(Pickup pickup) {
        return (pickup.getCategories() ^ this.getRestrictions()) != 0;
    }

    public int countAccepts(Pickup pickup) {
        return countSetBits(pickup.getCategories() ^ this.getRestrictions());
    }

    public int countSetBits(int n) { // Kernighan's algorithm
        int count = 0;

        while (n != 0) {
            n &= (n - 1);
            count++;
        }

        return count;
    }

    public boolean isOpenAt(LocalDateTime pickupAt) {
        DayOfWeek day = pickupAt.getDayOfWeek();
        int pickupTime = 2 ^ pickupAt.getHour();

        switch (day) {
            case MONDAY:
                return (pickupTime & this.getMonday()) != 0;
            case TUESDAY:
                return (pickupTime & this.getTuesday()) != 0;
            case WEDNESDAY:
                return (pickupTime & this.getWednesday()) != 0;
            case THURSDAY:
                return (pickupTime & this.getThursday()) != 0;
            case FRIDAY:
                return (pickupTime & this.getFriday()) != 0;
            case SATURDAY:
                return (pickupTime & this.getSaturday()) != 0;
            case SUNDAY:
                return (pickupTime & this.getSunday()) != 0;
            default:
                throw new IllegalArgumentException("Not a day of the week.");
        }
    }
}
