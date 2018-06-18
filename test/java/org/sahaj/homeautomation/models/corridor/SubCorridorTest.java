package org.sahaj.homeautomation.models.corridor;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sahaj.hotelautomation.models.corridors.Corridor;
import org.sahaj.hotelautomation.models.corridors.SubCorridor;

import static org.junit.Assert.assertEquals;

public class SubCorridorTest {

    private Corridor corridor;
    private static final int subCorridorNumber = 1;

    @Before
    public void initialize() {
        corridor = new SubCorridor(subCorridorNumber);
    }

    @Test
    public void getPowerConsumedTest() {

        assertEquals(corridor.getPowerConsumed(), 10);
    }


    @Test
    public void withACPowerConsumedTest() {

        corridor.getAirConditioner().turnOff();
        assertEquals(corridor.getPowerConsumed(), 0);
    }

    @After
    public void terminate() {
        // Should kill hotel object in GC
        corridor = null;
    }

}
