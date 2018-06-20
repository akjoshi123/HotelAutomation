package org.sahaj.homeautomation.limitations;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.limitations.PowerConsumptionLimitsCriteria;
import org.sahaj.hotelautomation.limitations.PowerLimitsCriteria;
import org.sahaj.hotelautomation.models.Floor;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Junit testing of PowerConsumptionLimit class.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class PowerConsumptionLimitsTest {

    private static Hotel hotel;
    private PowerLimitsCriteria powerLimitsCriteria;
    private static int floorCount = 2;
    private static int mainCorridorCount = 2;
    private static int subCorridorCount = 3;

    @Before
    public void initialize() {
        hotel = new Hotel.HotelBuilder("Westin").addFloor(floorCount).addMainCorridor(mainCorridorCount).addSubCorridor(subCorridorCount).build();
        powerLimitsCriteria = new PowerConsumptionLimitsCriteria();
    }

    @After
    public void terminate() {
        // Should kill hotel object in GC
        hotel = null;
        powerLimitsCriteria = null;
    }


    /**
     * Check is power consumption is calculated correctly.
     */
    @Test
    public void floorPowerConsumptionTest() {

        int corridorNumber = 1;
        Floor firstFloor = hotel.getFloors().get(corridorNumber);

        assertEquals(60, powerLimitsCriteria.getPowerAllowedPerFloor(firstFloor));
    }

    /**
     * Check is power consumption is within limits.
     */
    @Test
    public void withinLimitsTest() {
        int corridorNumber = 1;
        Floor firstFloor = hotel.getFloors().get(corridorNumber);

        firstFloor.getSubCorridors().get(2).getAirConditioner().turnOff();
        assertTrue(powerLimitsCriteria.isWithinLimit(firstFloor));

        firstFloor.getSubCorridors().get(2).getAirConditioner().turnOn();
        assertFalse(powerLimitsCriteria.isWithinLimit(firstFloor));
    }


}
