package org.sahaj.homeautomation.models.corridor;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sahaj.hotelautomation.models.corridors.Corridor;
import org.sahaj.hotelautomation.models.corridors.MainCorridor;

import static org.junit.Assert.assertEquals;

public class MainCorridorTest {

    private Corridor corridor;
    private static final int mainCorridorNumber = 1;

    @Before
    public void initialize() {
        corridor = new MainCorridor(mainCorridorNumber);
    }

    @Test
    public void getPowerConsumedTest() {

        assertEquals(corridor.getPowerConsumed(), 15);
    }

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
