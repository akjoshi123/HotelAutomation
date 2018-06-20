package org.sahaj.homeautomation.models.corridor;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sahaj.hotelautomation.models.corridors.Corridor;
import org.sahaj.hotelautomation.models.corridors.MainCorridor;

import static org.junit.Assert.assertEquals;

/**
 * Junit testing of  MainCorridor class.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class MainCorridorTest {

    private Corridor corridor;
    private static final int mainCorridorNumber = 1;

    @Before
    public void initialize() {
        corridor = new MainCorridor(mainCorridorNumber);
    }

    /**
     * Check if power consumed is set correctly.
     */
    @Test
    public void getPowerConsumedTest() {

        assertEquals(corridor.getPowerConsumed(), 15);
    }

    /**
     * Check if power consumed is set correctly with AC turned as OFF.
     */
    @Test
    public void withACPowerConsumedTest() {

        corridor.getAirConditioner().turnOff();
        assertEquals(corridor.getPowerConsumed(), 5);
    }

    @After
    public void terminate() {
        // Should kill hotel object in GC
        corridor = null;
    }
}
