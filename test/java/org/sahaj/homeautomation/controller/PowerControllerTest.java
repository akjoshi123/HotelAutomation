package org.sahaj.homeautomation.controller;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.controller.PowerController;
import org.sahaj.hotelautomation.models.Motion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Junit testing of PowerController class.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class PowerControllerTest {

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

    /**
     * Checks when a motion is detected, a light bulb is switched ON and a AC is turned OFF since its exceeding power limits..
     */
    @Test
    public void checkIfACTurnedONTest() {

        int motionFloor = 1;
        int motionSubcorridor = 2;

        Motion motion = new Motion(motionFloor, motionSubcorridor);
        PowerController powerController = new PowerController(hotel);

        powerController.update(motion, null);

        assertTrue(hotel.getFloors().get(motionFloor).getSubCorridors().get(motionSubcorridor).getLight().getStatus());
        assertFalse(hotel.getFloors().get(motionFloor).getSubCorridors().get(1).getAirConditioner().getStatus());
    }

    /**
     * Checks when a motion is detected, a light bulb is switched ON and a AC is not turned OFF since its not exceeding power limits..
     */
    @Test
    public void checkIfACNotTurnedONTest() {

        int motionFloor = 1;
        int motionSubcorridor = 2;
        int anotherMotionSubcorridor = 1;

        Motion motion = new Motion(motionFloor, motionSubcorridor);
        PowerController powerController = new PowerController(hotel);

        powerController.update(motion, null);

        assertTrue(hotel.getFloors().get(motionFloor).getSubCorridors().get(motionSubcorridor).getLight().getStatus());
        assertFalse(hotel.getFloors().get(motionFloor).getSubCorridors().get(1).getAirConditioner().getStatus());

        motion.setSubCorridorNumber(anotherMotionSubcorridor);
        powerController.update(motion, null);

        assertTrue(hotel.getFloors().get(motionFloor).getSubCorridors().get(anotherMotionSubcorridor).getLight().getStatus());
        assertTrue(hotel.getFloors().get(motionFloor).getSubCorridors().get(2).getAirConditioner().getStatus());
        assertFalse(hotel.getFloors().get(motionFloor).getSubCorridors().get(1).getAirConditioner().getStatus());
    }

    /**
     * For a single sub corridor, when a motion is detected, a light bulb is switched ON and a AC is turned OFF since its exceeding power limits.
     */
    @Test
    public void singleSubcorridorTest() {

        Hotel newHotel = new Hotel.HotelBuilder("Westin").addFloor(2).addMainCorridor(1).addSubCorridor(1).build();
        int motionFloor = 1;
        int motionSubcorridor = 1;

        Motion motion = new Motion(motionFloor, motionSubcorridor);
        PowerController powerController = new PowerController(newHotel);

        powerController.update(motion, null);

        assertTrue(newHotel.getFloors().get(motionFloor).getSubCorridors().get(motionSubcorridor).getLight().getStatus());
        assertFalse(newHotel.getFloors().get(motionFloor).getSubCorridors().get(motionSubcorridor).getAirConditioner().getStatus());
    }
}
