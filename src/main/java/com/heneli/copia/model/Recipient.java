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
        // Let 'food accepted' = 'all food' xor 'food rejected'
        // If 'food accepted' & 'food provided' != 0,
        // Then the 'food provided' is a subset of 'food accepted'
        // Thus, recipient does reject the food provided by pickup

        // all food = 63 = 0b111111, single bit for each category/restriction
        return ((63 ^ this.getRestrictions()) & pickup.getCategories()) != 0;
    }

    public int countAccepts(Pickup pickup) {
        return countSetBits((63 ^ this.getRestrictions()) & pickup.getCategories());
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
        int pickupTime = (int) Math.pow(2, pickupAt.getHour()-8); // recipient 0th bit=8-9 AM .. 16th-bit=11-12 AM

        switch (day) {
            case MONDAY:
                return isOpenAt(pickupTime, this.getMonday());
            case TUESDAY:
                return isOpenAt(pickupTime, this.getTuesday());
            case WEDNESDAY:
                return isOpenAt(pickupTime, this.getWednesday());
            case THURSDAY:
                return isOpenAt(pickupTime, this.getThursday());
            case FRIDAY:
                return isOpenAt(pickupTime, this.getFriday());
            case SATURDAY:
                return isOpenAt(pickupTime, this.getSaturday());
            case SUNDAY:
                return isOpenAt(pickupTime, this.getSunday());
            default:
                throw new IllegalArgumentException("Not a day of the week.");
        }
    }

    public boolean isOpenAt(int pickupTime, int openTimes) {
        return (pickupTime & openTimes) != pickupTime;
    }
}
