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

    @Before
    public void initialize() {
        hotel = new Hotel.HotelBuilder("Westin").addFloor(2).addMainCorridor(2).addSubCorridor(3).build();

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
