package com.heneli.copia.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinMatchersTest {

    private int provide1, provide2, provide3, provide4, provide5;

    private int accept1, accept2, accept3, accept4, accept5;

    @Before
    public void setUp() throws Exception {
        provide1 = 56;  // 0b111000
        provide2 = 8;   // 0b001000
        provide3 = 11;  // 0b001011
        provide4 = 0;   // 0b000000
        provide5 = 63;  // 0b111111

        accept1 = 56;
        accept2 = 8;
        accept3 = 11;
        accept4 = 0;
        accept5 = 63;
    }

    @Test
    public void isOnlyOneToOneBinMatch() {
        assertTrue(BinMatchers.isOnlyOneToOneBinMatch(provide1, accept1));
        assertTrue(BinMatchers.isOnlyOneToOneBinMatch(provide2, accept2));
        assertTrue(BinMatchers.isOnlyOneToOneBinMatch(provide3, accept3));

        assertTrue(BinMatchers.isOnlyOneToOneBinMatch(provide4, accept1));
        assertTrue(BinMatchers.isOnlyOneToOneBinMatch(provide4, accept2));
        assertTrue(BinMatchers.isOnlyOneToOneBinMatch(provide4, accept3));
        assertTrue(BinMatchers.isOnlyOneToOneBinMatch(provide4, accept4));
        assertTrue(BinMatchers.isOnlyOneToOneBinMatch(provide4, accept5));

        assertFalse(BinMatchers.isOnlyOneToOneBinMatch(provide5, accept1));
        assertFalse(BinMatchers.isOnlyOneToOneBinMatch(provide5, accept2));
        assertFalse(BinMatchers.isOnlyOneToOneBinMatch(provide5, accept3));
        assertFalse(BinMatchers.isOnlyOneToOneBinMatch(provide5, accept4));

        assertTrue(BinMatchers.isOnlyOneToOneBinMatch(provide5, accept5));
    }

    @Test
    public void isOnlyOneToManyBinMatch() {
        assertTrue(BinMatchers.isOnlyOneToTwoBinMatch(provide1, accept1, accept2));
        assertTrue(BinMatchers.isOnlyOneToTwoBinMatch(provide1, accept2, accept1));
        assertTrue(BinMatchers.isOnlyOneToTwoBinMatch(provide1, accept1, accept3));

        assertFalse(BinMatchers.isOnlyOneToTwoBinMatch(provide1, accept2, accept3));


        assertTrue(BinMatchers.isOnlyOneToTwoBinMatch(provide4, accept1, 0));
        assertTrue(BinMatchers.isOnlyOneToTwoBinMatch(provide4, accept2, 0));
        assertTrue(BinMatchers.isOnlyOneToTwoBinMatch(provide4, accept3, 0));
        assertTrue(BinMatchers.isOnlyOneToTwoBinMatch(provide4, accept4, 0));
        assertTrue(BinMatchers.isOnlyOneToTwoBinMatch(provide4, accept5, 0));

        assertFalse(BinMatchers.isOnlyOneToTwoBinMatch(provide5, accept1, 0));
        assertFalse(BinMatchers.isOnlyOneToTwoBinMatch(provide5, accept2, 0));
        assertFalse(BinMatchers.isOnlyOneToTwoBinMatch(provide5, accept3, 0));
        assertFalse(BinMatchers.isOnlyOneToTwoBinMatch(provide5, accept4, 0));

        assertTrue(BinMatchers.isOnlyOneToTwoBinMatch(provide5, accept5, 0));
    }
}