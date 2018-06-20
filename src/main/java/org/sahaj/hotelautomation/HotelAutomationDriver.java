package org.sahaj.hotelautomation;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.controller.MotionController;
import org.sahaj.hotelautomation.controller.PowerController;
import org.sahaj.hotelautomation.crons.RestoreCron;
import org.sahaj.hotelautomation.inputs.Input;
import org.sahaj.hotelautomation.models.inputs.InitialInput;
import org.sahaj.hotelautomation.models.Motion;
import org.sahaj.hotelautomation.models.inputs.MotionInput;

/**
 * Entry point to this application.
 * 1) Accepts user inputs for hotel details and motion details.
 * 2) Triggers the restore cron once hotel details are entered.
 * 3) Publishes motion event which will be consumed by PowerController.
 * 4) Type 'exit' to kill the application.
 * 
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class HotelAutomationDriver {

    private Hotel hotel = null;

    public static void main(String args[]) {

        HotelAutomationDriver hotelAutomationDriver = new HotelAutomationDriver();
        hotelAutomationDriver.acceptInput();
    }

    private void acceptInput() {

        Motion motion = new Motion();
        PowerController powerController;

        try {
            InitialInput hotelState = Input.getInitialInput();

            if (hotelState == null) {
                System.err.println("Initial Input i.e. count of Floor , Maincorridor and Subcorridor must be atleast 1.");
                return;
            }

            int floorCount = hotelState.getFloor();
            int mainCorridorsPerFloor = hotelState.getMainCorridorCount();
            int subCorridorsPerFloor = hotelState.getSubCorridorCount();

            hotel = new Hotel.HotelBuilder("Westin").addFloor(floorCount).addMainCorridor(mainCorridorsPerFloor).addSubCorridor(subCorridorsPerFloor).build();
            hotel.print();

            powerController = new PowerController(hotel);
            MotionController motionController = new MotionController(motion, powerController);
            RestoreCron rc = new RestoreCron(powerController, hotel);

            while (true) {
                MotionInput motionInput = Input.getMotionInput(hotel);

                if (motionInput == null) {
                    System.err.println("Input floor/subcorridor is not in expected range");
                    continue;
                }

                int floorNumber = motionInput.getFloor();
                int corridor = motionInput.getSubCorridor();
                motion.setFloorNumber(floorNumber);
                motion.setSubCorridorNumber(corridor);

                motionController.publishMotionEvent();
            }
        } catch (Exception ex) {
            ex.getStackTrace();
            System.out.println(ex.getMessage());
        }
    }

}
