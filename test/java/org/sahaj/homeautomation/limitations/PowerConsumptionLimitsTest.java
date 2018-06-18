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

    @Before
    public void initialize() {
        hotel = new Hotel.HotelBuilder("Westin").addFloor(2).addMainCorridor(2).addSubCorridor(3).build();
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

        assertEquals(powerLimits.getPowerAllowedPerFloor(firstFloor), 60);
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
