package org.sahaj.hotelautomation.constants;

/**
 * Constants which stores all the configurable values of this Hotel automation application.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class Constants {

    // String for printing the status of AC and light bulb.
    public static String onStatus = "ON";
    public static String offStatus = "OFF";

    // Maximum power consumption allowed at main corridor
    public static int powerConsumptionAllowedMaincorridor = 15;

    // Maximum power consumption allowed at sub corridor
    public static int powerConsumptionAllowedSubcorridor = 10;

    // Power consumed by a single AC
    public static int powerConsumptionAC = 10;

    // Power consumed by a single Light bulb.
    public static int powerConsumptionLight = 5;

    // Interval in seconds when the restore cron should trigger.
    public static int cronIntervalSeconds = 5;

    // Number of threads in the executor service of the cron.
    public static int cronThreads = 1;

    // Interval in minutes till when the light bulb is active after the motion is detected.
    public static int lightOnIntervalMinutes = 1;

}
