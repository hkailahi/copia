package com.heneli.copia.model;

import lombok.*;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)

public class Pickup extends User {

    private int pickupId;
    private int categories;
    private LocalDateTime pickupAt;
    private ZoneId timeZoneId;

    @Getter(AccessLevel.NONE)
    @Transient
    private ZonedDateTime zonedDateTime;

    public Pickup(int pickupId, int categories, LocalDateTime pickupAt, ZoneId timeZoneId) {
        this.pickupId = pickupId;
        this.categories = categories;
        this.pickupAt = pickupAt;
        this.timeZoneId = timeZoneId;
        this.zonedDateTime = getZonedDateTime();
    }

    public ZonedDateTime getZonedDateTime() {
        zonedDateTime = ZonedDateTime.of(getPickupAt(), getTimeZoneId());
        return zonedDateTime;
    }
}
