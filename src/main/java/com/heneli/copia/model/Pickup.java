package com.heneli.copia.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Entity
public class Pickup extends User {

    @Id
    private int pickupId;
    private int categories;
    private LocalDateTime pickupAt;
    private ZoneId timeZoneId; // Maybe string

    @Getter(AccessLevel.NONE)
    @Transient
    private ZonedDateTime zonedDateTime;

    public ZonedDateTime getZonedDateTime() {
        zonedDateTime = ZonedDateTime.of(getPickupAt(), getTimeZoneId());
        return zonedDateTime;
    }
}
