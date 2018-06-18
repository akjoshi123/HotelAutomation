package org.sahaj.homeautomation.builder;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.limitations.PowerConsumptionLimits;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

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


    @Test
    public void addFloorTest() {
        int floorCount = 2;

        Hotel hotel = hotelBuilder.addFloor(floorCount).build();

        assertEquals(hotel.getFloors().size() , floorCount);
    }

    @Test
    public void addMainCorridorTest() {
        int mainCorridor = 1;

        Hotel hotel = hotelBuilder.addFloor(1).addMainCorridor(mainCorridor).build();

        assertEquals(hotel.getFloors().get(1).getMainCorridors().size() , mainCorridor);
    }

    @Test
    public void addSubCorridorTest() {
        int subCorridor = 1;

        Hotel hotel = hotelBuilder.addFloor(1).addSubCorridor(subCorridor).build();

        assertEquals(hotel.getFloors().get(1).getSubCorridors().size() , subCorridor);
    }



    @Test
    public void addMainAndSubCorridorTest() {
        int mainCorridor = 1;
        int subCorridor = 2;

        Hotel hotel = hotelBuilder.addFloor(1).addMainCorridor(mainCorridor).addSubCorridor(subCorridor).build();

        assertEquals(hotel.getFloors().get(1).getSubCorridors().size() , subCorridor);
        assertEquals(hotel.getFloors().get(1).getMainCorridors().size() , mainCorridor);
    }

    @Test
    public void hotelBuilderTest() {
        Hotel hotel = hotelBuilder.build();

        assertNotNull(hotel);
    }

    @Test
    public void hotelNameTest() {
        Hotel hotel = hotelBuilder.build();

        assertEquals(hotel.getHotelName(), hotelName);

    }
}
