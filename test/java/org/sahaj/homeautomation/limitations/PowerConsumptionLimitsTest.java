package org.sahaj.homeautomation.limitations;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.limitations.PowerConsumptionLimits;
import org.sahaj.hotelautomation.limitations.PowerLimits;
import org.sahaj.hotelautomation.models.Floor;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class PowerConsumptionLimitsTest {

    private static Hotel hotel;
    private PowerLimits powerLimits;
    private static int floorCount = 2;
    private static int mainCorridorCount = 2;
    private static int subCorridorCount = 3;

    @Before
    public void initialize() {
        hotel = new Hotel.HotelBuilder("Westin").addFloor(floorCount).addMainCorridor(mainCorridorCount).addSubCorridor(subCorridorCount).build();
        powerLimits = new PowerConsumptionLimits();
    }

    @After
    public void terminate() {
        // Should kill hotel object in GC
        hotel = null;
        powerLimits = null;
    }


    @Test
    public void floorPowerConsumptionTest() {

        int corridorNumber = 1;
        Floor firstFloor = hotel.getFloors().get(corridorNumber);

        assertEquals(60, powerLimits.getPowerAllowedPerFloor(firstFloor));
    }

    @Test
    public void withinLimitsTest() {
        int corridorNumber = 1;
        Floor firstFloor = hotel.getFloors().get(corridorNumber);

        firstFloor.getSubCorridors().get(2).getAirConditioner().turnOff();
        assertTrue(powerLimits.isWithinLimit(firstFloor));

        firstFloor.getSubCorridors().get(2).getAirConditioner().turnOn();
        assertFalse(powerLimits.isWithinLimit(firstFloor));
    }


}
