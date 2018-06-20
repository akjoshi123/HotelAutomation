package org.sahaj.hotelautomation.utils;

import org.sahaj.hotelautomation.models.Floor;
import org.sahaj.hotelautomation.models.corridors.Corridor;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PowerUtils {

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

    public static void turnONRandomAC(HashMap<Integer, Corridor> subCorridors) {
        Optional<Map.Entry<Integer, Corridor>> randomSubCorridor = subCorridors.entrySet().stream().filter(subCorridor -> subCorridor.getValue().getAirConditioner().getStatus() == false).findFirst();

        randomSubCorridor.get().getValue().getAirConditioner().turnOn();
    }
}
