package org.sahaj.homeautomation.models.corridor.electronics;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sahaj.hotelautomation.models.electronics.LightBulb;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Junit testing of LightBulb class.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class LightBulbTest {

    private LightBulb lightBulb;

    @Before
    public void initialize() {
        lightBulb = new LightBulb(5);
    }

    /**
     * Check if Light Bulb is turned OFF and ON
     */
    @Test
    public void onOffTest() {

        lightBulb.turnOn();
        assertTrue(lightBulb.getStatus());

        lightBulb.turnOff();
        assertFalse(lightBulb.getStatus());

    }

    @After
    public void terminate() {
        // Should kill hotel object in GC
        lightBulb = null;
    }
}
