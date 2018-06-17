package org.sahaj.hotelautomation;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.controller.PowerController;
import org.sahaj.hotelautomation.crons.RevertCron;
import org.sahaj.hotelautomation.inputs.Input;
import org.sahaj.hotelautomation.models.InitialInput;
import org.sahaj.hotelautomation.models.Motion;
import org.sahaj.hotelautomation.models.MotionInput;

public class HomeAutomationDriver {

    private Hotel hotel = null;
    public static void main(String args[]) {
//
//        if(args.length != 1) {
//            System.out.println("Usage:: Input file not present in argument.");
//            return;
//        }

        HomeAutomationDriver homeAutomationDriver = new HomeAutomationDriver();
        homeAutomationDriver.acceptInput();
    }

    private void acceptInput() {

        String line = null;
        Boolean firstTime = true;

        Motion m = new Motion();
        PowerController p;


        try {
//            FileReader fileReader =
//                    new FileReader(filePath);
//
//            BufferedReader bufferedReader =
//                    new BufferedReader(fileReader);

//        //    while((line = bufferedReader.readLine()) != null) {
//                if(firstTime) {
//                    firstTime = false;
                    InitialInput hotelState = Input.getInitialInput();

               //     try {
                        int floorCount = hotelState.getFloor();
                        int mainCorridorsPerFloor = hotelState.getMainCorridorCount();
                        int subCorridorsPerFloor = hotelState.getSubCorridorCount();

                        hotel = new Hotel.HotelBuilder("Westin").addFloor(floorCount).addMainCorridor(mainCorridorsPerFloor).addSubCorridor(subCorridorsPerFloor).build();


                        hotel.print();
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println("Input file does not contain input in correct format.");
//
//                    }
             //   } else {
            p = new PowerController(hotel);
            m.addObserver(p);
            RevertCron rc = new RevertCron(p, hotel);

            while(true) {
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
               // }

           // }

          //  bufferedReader.close();
        }
        catch(Exception ex) {
            ex.getStackTrace();
            System.out.println(ex.getMessage());
        }
    }

}
