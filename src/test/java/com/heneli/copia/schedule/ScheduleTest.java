package com.heneli.copia.schedule;

import com.heneli.copia.model.Pickup;
import com.heneli.copia.model.Recipient;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ScheduleTest {

    private Schedule schedule;
    private List<Pickup> pickups;
    private List<Recipient> recipients;

    @Before
    public void setUp() throws Exception {


        Pickup p1 = new Pickup(1, 63, LocalDateTime.now(), ZoneId.of("America/Los_Angeles"));
        Pickup p2 = new Pickup(1, 0, LocalDateTime.now(), ZoneId.of("America/Los_Angeles"));

        // 63 = 0b111111 -> takes no food
        // 65535 = 0b11..11 -> open at all hours
        // rejects all food, open at all hours
        Recipient r1 = new Recipient(1, 63, 65535, 65535, 65535, 65535, 65535, 65535, 65535);
        // accepts all food, open at all hours
        Recipient r2 = new Recipient(2, 0, 65535, 65535, 65535, 65535, 65535, 65535, 65535);
        // accept all food, closed at all hours
        Recipient r3 = new Recipient(3, 0, 0, 0, 0, 0, 0, 0, 0);
        // rejects all food, closed at all hours
        Recipient r4 = new Recipient(3, 63, 0, 0, 0, 0, 0, 0, 0);


        pickups = new ArrayList<>();
        pickups.add(p1);
        pickups.add(p2);

        recipients = new ArrayList<>();
        recipients.add(r1);
        recipients.add(r2);
        recipients.add(r3);
        recipients.add(r4);

        schedule = new Schedule(pickups, recipients);
    }

    @Test
    public void getMatches() {
    }

    @Test
    public void generateSchedule() {
    }

    @Test
    public void findQualifiedRecipients() {
    }

    @Test
    public void getAllRecipientMatches() {
    }
}