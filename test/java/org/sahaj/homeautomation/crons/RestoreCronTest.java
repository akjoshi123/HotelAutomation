package org.sahaj.homeautomation.crons;


import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.controller.MotionController;
import org.sahaj.hotelautomation.controller.PowerController;
import org.sahaj.hotelautomation.crons.RestoreCron;
import org.sahaj.hotelautomation.models.Motion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestoreCronTest {

    private static Hotel hotel;
    private static int floorCount = 2;
    private static int mainCorridorCount = 2;
    private static int subCorridorCount = 3;
    private PowerController powerController;
    private MotionController motionController;
    private static int motionFloor = 1;
    private static int motionSubcorridor = 2;
    private static RestoreCron restoreCron;

    @Before
    public void initialize() {
        Motion motion = new Motion(motionFloor, motionSubcorridor);

        hotel = new Hotel.HotelBuilder("Westin").addFloor(floorCount).addMainCorridor(mainCorridorCount).addSubCorridor(subCorridorCount).build();
        powerController = new PowerController(hotel);
        motionController = new MotionController(motion, powerController);

        RestoreCron rc = new RestoreCron(powerController, hotel);

    }

    @After
    public void terminate() {
        // Should kill hotel object in GC
        hotel = null;
        powerController = null;
        motionController = null;
    }

    @Test
    public void cronRunTest() {
        motionController.publishMotionEvent();
        assertTrue(hotel.getFloors().get(motionFloor).getSubCorridors().get(motionSubcorridor).getLight().getStatus());

        try {
            Thread.sleep((Constants.lightOnIntervalMinutes * 60 + 5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(hotel.getFloors().get(motionFloor).getSubCorridors().get(motionSubcorridor).getLight().getStatus());

    }
}
