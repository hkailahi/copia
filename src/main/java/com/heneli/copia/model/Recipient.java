package com.heneli.copia.model;

import com.heneli.copia.util.BinMatchers;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)

public class Recipient extends User {

    private int recipientId;
    private int restrictions;
    private int Sunday;
    private int Monday;
    private int Tuesday;
    private int Wednesday;
    private int Thursday;
    private int Friday;
    private int Saturday;

    public Recipient(int recipientId, int restrictions, int sunday, int monday, int tuesday, int wednesday, int thursday, int friday, int saturday) {
        this.recipientId = recipientId;
        this.restrictions = restrictions;
        Sunday = sunday;
        Monday = monday;
        Tuesday = tuesday;
        Wednesday = wednesday;
        Thursday = thursday;
        Friday = friday;
        Saturday = saturday;
    }

    public boolean accepts(Pickup pickup) {
        // Let 'food accepted' = 'all food' xor 'food rejected'
        // If 'food accepted' & 'food provided' != 0,
        // Then the 'food provided' is a subset of 'food accepted'
        // Thus, recipient does reject the food provided by pickup

        // all food = 63 = 0b111111, single bit for each category/restriction
        int aV = 63 ^ this.getRestrictions(), pV = pickup.getCategories();
        return (pV & aV) != 0;
    }

    public boolean isOpenAt(LocalDateTime pickupAt) {
        DayOfWeek day = pickupAt.getDayOfWeek();
        int pickupHour = 1 << (pickupAt.getHour()-8); // recipient 0th bit=8-9 AM .. 16th-bit=11-12 AM

        if (pickupAt.getMinute() > 0 || pickupAt.getSecond() > 0) {
            pickupHour |= 1 << (pickupAt.getHour() - 7); // ex. 8:30 to 9:30 = 0b00..011
        }

        switch (day) {
            case MONDAY:
                return isOpenAt(pickupHour, this.getMonday());
            case TUESDAY:
                return isOpenAt(pickupHour, this.getTuesday());
            case WEDNESDAY:
                return isOpenAt(pickupHour, this.getWednesday());
            case THURSDAY:
                return isOpenAt(pickupHour, this.getThursday());
            case FRIDAY:
                return isOpenAt(pickupHour, this.getFriday());
            case SATURDAY:
                return isOpenAt(pickupHour, this.getSaturday());
            case SUNDAY:
                return isOpenAt(pickupHour, this.getSunday());
            default:
                throw new IllegalArgumentException("Not a day of the week.");
        }
    }

    private boolean isOpenAt(int pickupTime, int openTimes) {
        return BinMatchers.isOnlyOneToOneBinMatch(pickupTime, openTimes);
    }
}
