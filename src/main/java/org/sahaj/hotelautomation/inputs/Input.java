package org.sahaj.hotelautomation.inputs;

import org.sahaj.hotelautomation.models.inputs.InitialInput;
import org.sahaj.hotelautomation.models.inputs.MotionInput;

import java.util.Scanner;

public class Input {

    private static final String FLOOR_COUNT_STRING = "Number of floors: ";
    private static final String MAIN_CORRIDOR_COUNT_STRING = "Main corridors per floor: ";
    private static final String SUB_CORRIDOR_COUNT_STRING = "Sub corridors per floor: ";

    private static final String INVALID_INPUT = "Invalid input: ";

    private static final String MOTION_INPUT = "Enter motion at floor and Sub corridor: ";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static InitialInput getInitialInput() {

        System.out.println(FLOOR_COUNT_STRING);

        int floorCount = getNextInt();
        System.out.println(MAIN_CORRIDOR_COUNT_STRING);
        int mainCorridorCount = getNextInt();
        System.out.println(SUB_CORRIDOR_COUNT_STRING);
        int subCorridorCount = getNextInt();

        InitialInput input = new InitialInput(floorCount, mainCorridorCount, subCorridorCount);

        return input;
    }

    public static MotionInput getMotionInput() {

        System.out.println(MOTION_INPUT);

        int floorNumber = getNextInt();
        int subCorridorNumber = getNextInt();

        MotionInput motionInput = new MotionInput(floorNumber, subCorridorNumber);
        return motionInput;
    }

    private static int getNextInt() {
        while (!SCANNER.hasNextInt()) {
            System.err.println(INVALID_INPUT);
            SCANNER.nextLine();
        }

        return SCANNER.nextInt();
    }
}
