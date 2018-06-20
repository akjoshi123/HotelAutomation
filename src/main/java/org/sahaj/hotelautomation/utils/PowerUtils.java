package org.sahaj.hotelautomation.utils;

import org.sahaj.hotelautomation.models.Floor;
import org.sahaj.hotelautomation.models.corridors.Corridor;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Utility class which helps finding required details of corridor.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class PowerUtils {

    /**
     * Finds a corridor to turn its AirConditioner OFF so that a LightBulb can be turned ON.
     *
     * @param corridor
     * @param subCorridors
     * @param subCorridorNumber
     * @return
     */
    public static Corridor findRandomCorridor(Corridor corridor, HashMap<Integer, Corridor> subCorridors, int subCorridorNumber) {
        Optional<Map.Entry<Integer, Corridor>> randomSubCorridor = subCorridors.entrySet().stream().filter(subCorridor -> subCorridor.getValue().getAirConditioner().getStatus() == true && subCorridor.getKey() != subCorridorNumber).findFirst();

        Corridor matchingCorridor;
        try {
            matchingCorridor = randomSubCorridor.get().getValue();
        } catch (NoSuchElementException exception) {
            matchingCorridor = corridor;
        }

        return matchingCorridor;
    }

    /**
     * Turns a AirConditioner ON if a LightBulb is turned OFF and current power consumption is within limits.
     *
     * @param subCorridors
     */
    public static void turnONRandomAC(HashMap<Integer, Corridor> subCorridors) {
        Optional<Map.Entry<Integer, Corridor>> randomSubCorridor = subCorridors.entrySet().stream().filter(subCorridor -> subCorridor.getValue().getAirConditioner().getStatus() == false).findFirst();

        randomSubCorridor.get().getValue().getAirConditioner().turnOn();
    }
}
