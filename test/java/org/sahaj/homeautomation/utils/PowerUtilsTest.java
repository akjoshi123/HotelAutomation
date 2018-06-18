package org.sahaj.homeautomation.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.models.Floor;
import org.sahaj.hotelautomation.models.corridors.Corridor;
import org.sahaj.hotelautomation.utils.PowerUtils;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PowerUtilsTest {

    private static Hotel hotel;
    private static int floorCount = 2;
    private static int mainCorridorCount = 2;
    private static int subCorridorCount = 3;

    @Before
    public void initialize() {
        hotel = new Hotel.HotelBuilder("Westin").addFloor(floorCount).addMainCorridor(mainCorridorCount).addSubCorridor(subCorridorCount).build();

    }

    @After
    public void terminate() {
        // Should kill hotel object in GC
        hotel = null;
    }

    @Test
    public void findRandomCorridorTest() {

        int corridorNumber = 1;
        Floor firstFloor = hotel.getFloors().get(corridorNumber);
        Corridor corridor = firstFloor.getSubCorridors().get(1);

        Corridor corridorAlternate = PowerUtils.findRandomCorridor(corridor, firstFloor.getSubCorridors(), corridorNumber);

        assertNotNull(corridorAlternate);

    }
}
