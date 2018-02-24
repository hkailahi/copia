package com.heneli.copia.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() throws Exception {
        user1 = new User();
        user1.setLatitude(100);
        user1.setLongitude(-100);

        user2 = new User();
        user2.setLatitude(150);
        user2.setLongitude(150);

        user3 = new User();
        user3.setLatitude(7);
        user3.setLongitude(5);
    }

    @Test
    public void distanceBetweenUsers() { // tests distance(User, User)

        System.out.println((int)user1.distance(user2));
        System.out.println((int)user1.distance(user3));
        System.out.println((int)user2.distance(user3));

        // assertEquals(Double, Double) is deprecated

        assertEquals((int)user1.distance(user2), (int)user2.distance(user1));
        assertEquals(0, (int)user1.distance(user1));
        assertEquals(4410, (int)user1.distance(user2));
        assertEquals(5563, (int)user1.distance(user3));
        assertEquals(2769, (int)user2.distance(user3));
    }
}