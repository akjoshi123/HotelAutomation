package org.sahaj.hotelautomation;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.controller.PowerController;
import org.sahaj.hotelautomation.crons.RevertCron;
import org.sahaj.hotelautomation.models.Motion;

import java.io.*;

public class Driver {

    private Hotel hotel = null;
    public static void main(String args[]) {

        if(args.length != 1) {
            System.out.println("Usage:: Input file not present in argument.");
            return;
        }

        Driver driver = new Driver();
        driver.acceptInput(args[0]);
    }

    private void acceptInput(String filePath) {

        String line = null;
        Boolean firstTime = true;

        Motion m = new Motion();
        PowerController p;


        try {
            FileReader fileReader =
                    new FileReader(filePath);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if(firstTime) {
                    firstTime = false;
                    String[] input = line.split(",");

                    try {
                        int floors = Integer.parseInt(input[0].trim());
                        int mainCorridorsPerFloor = Integer.parseInt(input[1].trim());
                        int subCorridorsPerFloor = Integer.parseInt(input[2].trim());

                        hotel = new Hotel.HotelBuilder("Westin").addFloor(floors).addMainCorridor(mainCorridorsPerFloor).addSubCorridor(subCorridorsPerFloor).build();


                        hotel.print();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Input file does not contain input in correct format.");

                    }
                } else {


                    String[] input = line.split(",");
                    p = new PowerController(hotel);
                    m.addObserver(p);

                    RevertCron rc = new RevertCron(p, hotel);

                    try {
                        int floors = Integer.parseInt(input[0].trim());
                        int corridor = Integer.parseInt(input[1].trim());

                        m.setFloorNumber(floors);
                        m.setSubCorridorNumber(corridor);

                        m.notifyObservers();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Input file does not contain input in correct format.");

                    }
                }

            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            filePath + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + filePath + "'");
            ex.printStackTrace();
        }
    }

}
