package org.sahaj.homeautomation.builder;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sahaj.hotelautomation.builder.Hotel;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Junit testing of HotelBuilder class.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class HotelBuilderTest {

    private Hotel.HotelBuilder hotelBuilder;
    private static final String hotelName = "Westin";

    @Before
    public void initialize() {
        hotelBuilder = new Hotel.HotelBuilder(hotelName);
    }

    @After
    public void terminate() {
        // Should kill hotel object in GC
        hotelBuilder = null;
    }


    /**
     * Checks if floor count is updated correctly.
     */
    @Test
    public void addFloorTest() {
        int floorCount = 2;

        Hotel hotel = hotelBuilder.addFloor(floorCount).build();

        assertEquals(floorCount, hotel.getFloors().size());
    }

    /**
     * Checks if main corridor count is updated correctly.
     */
    @Test
    public void addMainCorridorTest() {
        int mainCorridor = 1;

        Hotel hotel = hotelBuilder.addFloor(1).addMainCorridor(mainCorridor).build();

        assertEquals(mainCorridor, hotel.getFloors().get(1).getMainCorridors().size());
    }

    /**
     * Checks if sub corridor count is updated correctly.
     */
    @Test
    public void addSubCorridorTest() {
        int subCorridor = 1;

        Hotel hotel = hotelBuilder.addFloor(1).addSubCorridor(subCorridor).build();

        assertEquals(subCorridor, hotel.getFloors().get(1).getSubCorridors().size());
    }


    /**
     * Checks if main and sub corridor count is updated correctly.
     */
    @Test
    public void addMainAndSubCorridorTest() {
        int mainCorridor = 1;
        int subCorridor = 2;

        Hotel hotel = hotelBuilder.addFloor(1).addMainCorridor(mainCorridor).addSubCorridor(subCorridor).build();

        assertEquals(subCorridor, hotel.getFloors().get(1).getSubCorridors().size());
        assertEquals(mainCorridor, hotel.getFloors().get(1).getMainCorridors().size());
    }

    /**
     * Checks if hotel object which is build is not null.
     */
    @Test
    public void hotelBuilderTest() {
        Hotel hotel = hotelBuilder.build();

        assertNotNull(hotel);
    }

    /**
     * Checks if hotel name is updated correctly.
     */
    @Test
    public void hotelNameTest() {
        Hotel hotel = hotelBuilder.build();

        assertEquals(hotelName, hotel.getHotelName());

    }
}
