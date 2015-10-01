package net.simplyadvanced.exampleunittestingminimum;

import android.test.InstrumentationTestCase;

/**
 * Sample use of native Android unit testing.
 */
public class MainTest extends InstrumentationTestCase {

    public void testAdd_positive() {
        int expected = 42;
        int reality = MainActivity.add(40, 2);
        assertEquals(expected, reality);
    }

    public void testAdd_negative() {
        int expected = 38;
        int reality = MainActivity.add(40, -2);
        assertEquals(expected, reality);
    }

    public void testAdd_overflow() {
        int expected = Integer.MIN_VALUE;
        int reality = MainActivity.add(Integer.MAX_VALUE, 1);
        assertEquals(expected, reality);
    }

}
