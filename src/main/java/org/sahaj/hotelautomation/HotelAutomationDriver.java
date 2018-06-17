package org.sahaj.hotelautomation;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.controller.PowerController;
import org.sahaj.hotelautomation.crons.RevertCron;
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

        Motion m = new Motion();
        PowerController p;


        try {
            InitialInput hotelState = Input.getInitialInput();

            int floorCount = hotelState.getFloor();
            int mainCorridorsPerFloor = hotelState.getMainCorridorCount();
            int subCorridorsPerFloor = hotelState.getSubCorridorCount();

            hotel = new Hotel.HotelBuilder("Westin").addFloor(floorCount).addMainCorridor(mainCorridorsPerFloor).addSubCorridor(subCorridorsPerFloor).build();


            hotel.print();

            p = new PowerController(hotel);
            m.addObserver(p);
            RevertCron rc = new RevertCron(p, hotel);

            while (true) {
                MotionInput motionInput = Input.getMotionInput();

                try {
                    int floorNumber = motionInput.getFloor();
                    int corridor = motionInput.getSubCorridor();

                    m.setFloorNumber(floorNumber);
                    m.setSubCorridorNumber(corridor);

                    m.notifyObservers();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Input file does not contain input in correct format.");

                }
            }
        } catch (Exception ex) {
            ex.getStackTrace();
            System.out.println(ex.getMessage());
        }
    }

}
