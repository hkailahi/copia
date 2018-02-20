package com.heneli.copia.schedule;

import com.heneli.copia.model.Pickup;
import com.heneli.copia.model.Recipient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ScheduleTest {

    private Schedule schedule;
    private List<Pickup> pickups;
    private List<Recipient> recipients;

    @Before
    public void setUp() throws Exception {
        Pickup p1 = new Pickup();
        Pickup p2 = new Pickup();
        Pickup p3 = new Pickup();

        Recipient r1 =  new Recipient();
        Recipient r2 =  new Recipient();
        Recipient r3 =  new Recipient();

        pickups = new ArrayList<>();
        pickups.add(p1);
        pickups.add(p2);
        pickups.add(p3);

        recipients = new ArrayList<>();
        recipients.add(r1);
        recipients.add(r2);
        recipients.add(r3);

        schedule = new Schedule(pickups, recipients);
    }

    @Test
    public void generateSchedule() {
    }

    @Test
    public void findMatches() {
    }

    @Test
    public void sortMatches() {
    }
}