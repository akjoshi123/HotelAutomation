package org.sahaj.hotelautomation.inputs;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.models.inputs.InitialInput;
import org.sahaj.hotelautomation.models.inputs.MotionInput;

import java.util.Scanner;

/**
 * Accepts the user input which is hotel details and motion details.
 * Will perform validations on the input as well.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class Input {

    private static final String FLOOR_COUNT_STRING = "Number of floors: ";
    private static final String MAIN_CORRIDOR_COUNT_STRING = "Main corridors per floor: ";
    private static final String SUB_CORRIDOR_COUNT_STRING = "Sub corridors per floor: ";

    private static final String INVALID_INPUT = "Invalid input: ";

    private static final String MOTION_INPUT_FLOOR = "Enter floor at where there is motion: ";
    private static final String MOTION_INPUT_SUBCORRIDOR = "Enter sub corridor at where there is motion: ";

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Gets the initial input containing hotel details from input stream.
     *
     * @return
     */
    public static InitialInput getInitialInput() {

        System.out.println(FLOOR_COUNT_STRING);

        int floorCount = getNextInt();
        System.out.println(MAIN_CORRIDOR_COUNT_STRING);
        int mainCorridorCount = getNextInt();
        System.out.println(SUB_CORRIDOR_COUNT_STRING);
        int subCorridorCount = getNextInt();

        InitialInput input = new InitialInput(floorCount, mainCorridorCount, subCorridorCount);

        if(!validInitialInput(input)) {
            return null;
        }

        return input;
    }

    /**
     * Gets the motion input details from input stream.
     *
     * @param hotel
     * @return
     */
    public static MotionInput getMotionInput(Hotel hotel) {

        System.out.println(MOTION_INPUT_FLOOR);

        int floorNumber = getNextInt();
        System.out.println(MOTION_INPUT_SUBCORRIDOR);

        int subCorridorNumber = getNextInt();

        MotionInput motionInput = new MotionInput(floorNumber, subCorridorNumber);

        if(!validInput(motionInput, hotel)) {
            return null;
        }
        return motionInput;
    }

    private static int getNextInt() {
        while (!SCANNER.hasNextInt()) {
            System.err.println(INVALID_INPUT);
            SCANNER.nextLine();
        }

        return SCANNER.nextInt();
    }

    /**
     * Validates the hotel details entered by user.
     *
     * @param hotelState
     * @return
     */
    private static boolean validInitialInput(InitialInput hotelState) {
        int floorCount = hotelState.getFloor();
        int mainCorridorsPerFloor = hotelState.getMainCorridorCount();
        int subCorridorsPerFloor = hotelState.getSubCorridorCount();

        if (floorCount <= 0 || mainCorridorsPerFloor <= 0 || subCorridorsPerFloor <= 0)
            return false;

        return true;
    }

    /**
     *  Validates the motion input details entered by user.
     *
     * @param motionInput
     * @param hotel
     * @return
     */
    private static boolean validInput(MotionInput motionInput, Hotel hotel) {

        if (motionInput.getFloor() <= 0 || motionInput.getSubCorridor() <= 0)
            return false;

        if (hotel.getFloors().size() < motionInput.getFloor())
            return false;

        if (hotel.getFloors().get(1).getSubCorridors().size() < motionInput.getSubCorridor())
            return false;

        return true;
    }
}
