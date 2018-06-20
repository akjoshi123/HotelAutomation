package org.sahaj.homeautomation.models.corridor.electronics;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sahaj.hotelautomation.models.electronics.AirConditioner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Junit testing of AirConditioner class.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class AirConditionerTest {

    private AirConditioner airConditioner;

    @Before
    public void initialize() {
        airConditioner = new AirConditioner(10);
    }

    /**
     * Check if AC is turned OFF and ON
     */
    @Test
    public void onOffTest() {

        airConditioner.turnOn();
        assertTrue(airConditioner.getStatus());

        airConditioner.turnOff();
        assertFalse(airConditioner.getStatus());

    }

    @After
    public void terminate() {
        // Should kill hotel object in GC
        airConditioner = null;
    }
}