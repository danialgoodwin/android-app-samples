package net.simplyadvanced.exampleunittesting;

import android.test.InstrumentationTestCase;

/**
 * A basic sample on how to create unit tests using the native Android SDK features.
 */
public class MainTest extends InstrumentationTestCase {

    public void testExample() throws Exception {
        final int expected = 5;
        final int reality = 5;
        assertEquals(expected, reality);
    }

    public void testExampleNull() throws Exception {
        final String cheese = null;
        assertNull(cheese);
    }

    public void testReturnSelf() throws Exception {
        String testString = "test";
        final String expected = "test";
        final String reality = MainActivity.returnSelf(testString);
        assertEquals(expected, reality);
    }

}
