package org.sahaj.hotelautomation;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.controller.MotionController;
import org.sahaj.hotelautomation.controller.PowerController;
import org.sahaj.hotelautomation.crons.RestoreCron;
import org.sahaj.hotelautomation.inputs.Input;
import org.sahaj.hotelautomation.models.inputs.InitialInput;
import org.sahaj.hotelautomation.models.Motion;
import org.sahaj.hotelautomation.models.inputs.MotionInput;

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

            int floorCount = hotelState.getFloor();
            int mainCorridorsPerFloor = hotelState.getMainCorridorCount();
            int subCorridorsPerFloor = hotelState.getSubCorridorCount();

            if (!validInitialInput(hotelState)) {
                System.err.println("Initial Input i.e. count of Floor , Maincorridor and Subcorridor must be atleast 1.");
                return;
            }

            hotel = new Hotel.HotelBuilder("Westin").addFloor(floorCount).addMainCorridor(mainCorridorsPerFloor).addSubCorridor(subCorridorsPerFloor).build();
            hotel.print();

            powerController = new PowerController(hotel);
            MotionController motionController = new MotionController(motion, powerController);

            RestoreCron rc = new RestoreCron(powerController, hotel);

            while (true) {
                MotionInput motionInput = Input.getMotionInput();


                if (!validInput(motionInput)) {
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

    private boolean validInitialInput(InitialInput hotelState) {
        int floorCount = hotelState.getFloor();
        int mainCorridorsPerFloor = hotelState.getMainCorridorCount();
        int subCorridorsPerFloor = hotelState.getSubCorridorCount();

        if (floorCount <= 0 || mainCorridorsPerFloor <= 0 || subCorridorsPerFloor <= 0)
            return false;

        return true;
    }

    private boolean validInput(MotionInput motionInput) {

        if (motionInput.getFloor() <= 0 || motionInput.getSubCorridor() <= 0)
            return false;

        if (hotel.getFloors().size() < motionInput.getFloor())
            return false;

        if (hotel.getFloors().get(1).getSubCorridors().size() < motionInput.getSubCorridor())
            return false;

        return true;
    }

}
