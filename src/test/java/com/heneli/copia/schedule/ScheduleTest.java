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
        //            int size = 0;
//            for(Map.Entry<Integer, List<Match>> entry : matchMap.entrySet()) {
//                size += entry.getValue().size();
//            }
//
//            System.out.println("Matches: " + size);

//            System.out.println("======= Start James Whitehouse =========");
//            List<Match> jamesWhitehouse;
//            jamesWhitehouse = getThreeRecipientMatches(pickups.get(59), nonSingleMatchMap.get(pickups.get(59).getPickupId()));
//            jamesWhitehouse.forEach(System.out::println);
//            System.out.println("======= End James Whitehouse =========");
//
//            System.out.println();
//
//            System.out.println("======= Start Dorothy Robbins =========");
//            List<Match> dorothy;
//            dorothy = getThreeRecipientMatches(pickups.get(9), nonSingleMatchMap.get(pickups.get(9).getPickupId()));
//            dorothy.forEach(System.out::println);
//            System.out.println("======= End Dorothy Robbins =========");
//
//            System.out.println();
//
//            System.out.println("======= Start Carroll Keys =========");
//            List<Match> carroll;
//            carroll = getThreeRecipientMatches(pickups.get(151), nonSingleMatchMap.get(pickups.get(151).getPickupId()));
//            carroll.forEach(System.out::println);
//            System.out.println("======= End Carroll Keys =========");
//
//            System.out.println();
//
//            System.out.println("======= Start David Long =========");
//            List<Match> davidLong;
//            davidLong = getThreeRecipientMatches(pickups.get(183), nonSingleMatchMap.get(pickups.get(183).getPickupId()));
//            davidLong.forEach(System.out::println);
//            System.out.println("======= End David Long =========");
    }

    @Test
    public void findMatches() {
    }

    @Test
    public void sortMatches() {
    }
}