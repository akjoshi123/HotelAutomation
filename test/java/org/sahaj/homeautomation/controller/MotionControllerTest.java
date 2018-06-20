package org.sahaj.homeautomation.controller;


import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.controller.MotionController;
import org.sahaj.hotelautomation.controller.PowerController;
import org.sahaj.hotelautomation.models.Motion;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Junit testing of MotionController class.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MotionControllerTest {

    private static Hotel hotel;
    private static int floorCount = 2;
    private static int mainCorridorCount = 2;
    private static int subCorridorCount = 3;
    private PowerController powerController;
    private MotionController motionController;

    @Before
    public void initialize() {
        hotel = new Hotel.HotelBuilder("Westin").addFloor(floorCount).addMainCorridor(mainCorridorCount).addSubCorridor(subCorridorCount).build();
        powerController = new PowerController(hotel);
    }

    @After
    public void terminate() {
        // Should kill hotel object in GC
        hotel = null;
        powerController = null;
        motionController = null;
    }

    /**
     * Checks if the light bulb is turned only for the mentioned sub corridor and not the order sub corridor.
     */
    @Test
    public void publishMotionEventTest() {
        int motionFloor = 1;
        int motionSubcorridor = 2;

        Motion motion = new Motion(motionFloor, motionSubcorridor);
        motionController = new MotionController(motion, powerController);

        motionController.publishMotionEvent();

        assertTrue(hotel.getFloors().get(motionFloor).getSubCorridors().get(motionSubcorridor).getLight().getStatus());
        assertFalse(hotel.getFloors().get(2).getSubCorridors().get(motionSubcorridor).getLight().getStatus());

    }

}
