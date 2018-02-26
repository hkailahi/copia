package com.heneli.copia.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RecipientTest {

    private Pickup pickup1;
    private Pickup pickup2;

    private Recipient recipient1;
    private Recipient recipient2;
    private Recipient recipient3;
    private Recipient recipient4;

    @Before
    public void setUp() throws Exception {

        pickup1 = new Pickup(1, 63, LocalDateTime.now(), ZoneId.of("America/Los_Angeles"));
        pickup2 = new Pickup(1, 0, LocalDateTime.now(), ZoneId.of("America/Los_Angeles"));

        // 63 = 0b111111 -> takes no food
        // 65535 = 0b11..11 -> open at all hours
        // rejects all food, open at all hours
        recipient1 = new Recipient(1, 63, 65535, 65535, 65535, 65535, 65535, 65535, 65535);
        // accepts all food, open at all hours
        recipient2 = new Recipient(2, 0, 65535, 65535, 65535, 65535, 65535, 65535, 65535);
        // accept all food, closed at all hours
        recipient3 = new Recipient(3, 0, 0, 0, 0, 0, 0, 0, 0);
        // rejects all food, closed at all hours
        recipient4 = new Recipient(3, 63, 0, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void accepts() {
        assertFalse(recipient1.accepts(pickup1));
        assertFalse(recipient1.accepts(pickup2));

        assertTrue(recipient2.accepts(pickup1));
        assertFalse(recipient2.accepts(pickup2));

        assertTrue(recipient3.accepts(pickup1));
        assertFalse(recipient3.accepts(pickup2));

        assertFalse(recipient4.accepts(pickup1));
        assertFalse(recipient4.accepts(pickup2));
    }

    @Test
    public void isOpenAt() {
        LocalDateTime pickupHour1 = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0));
        LocalDateTime pickupHour2 = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0));
        LocalDateTime pickupHour3 = LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30));
        LocalDateTime pickupHour4 = LocalDateTime.of(LocalDate.now(), LocalTime.of(5, 0)); // Hours start at 8, should fail

        assertTrue(recipient1.isOpenAt(pickupHour1));
        assertTrue(recipient1.isOpenAt(pickupHour2));
        assertTrue(recipient1.isOpenAt(pickupHour3));
        assertFalse(recipient1.isOpenAt(pickupHour4));

        assertTrue(recipient2.isOpenAt(pickupHour1));
        assertTrue(recipient2.isOpenAt(pickupHour2));
        assertTrue(recipient2.isOpenAt(pickupHour3));
        assertFalse(recipient2.isOpenAt(pickupHour4));

        assertFalse(recipient3.isOpenAt(pickupHour1));
        assertFalse(recipient3.isOpenAt(pickupHour2));
        assertFalse(recipient3.isOpenAt(pickupHour3));
        assertFalse(recipient3.isOpenAt(pickupHour4));

        assertFalse(recipient4.isOpenAt(pickupHour1));
        assertFalse(recipient4.isOpenAt(pickupHour2));
        assertFalse(recipient4.isOpenAt(pickupHour3));
        assertFalse(recipient4.isOpenAt(pickupHour4));
    }
}