package org.sahaj.hotelautomation.limitations;

import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.models.Corridors.Corridor;
import org.sahaj.hotelautomation.models.Floor;

import java.util.HashMap;
import java.util.List;

public class PowerConsumptionLimits implements PowerLimits {


    @Override
    public int getPowerAllowedPerFloor(Floor floor) {
        int mainCorridorSize = floor.getMainCorridors().size();
        int subCorridorSize = floor.getSubCorridors().size();

        return mainCorridorSize * Constants.powerConsumptionAllowedMaincorridor + subCorridorSize * Constants.powerConsumptionAllowedSubcorridor;
    }

    @Override
    public boolean isWithinLimit(Floor floor) {

        HashMap<Integer, Corridor> mainCorridors = floor.getMainCorridors();
        HashMap<Integer, Corridor> subCorridors = floor.getSubCorridors();

        int mainCorridorPowerConsumption = mainCorridors.entrySet().stream().mapToInt(x -> x.getValue().getPowerConsumed()).sum();
        int subCorridorPowerConsumption = subCorridors.entrySet().stream().mapToInt(x -> x.getValue().getPowerConsumed()).sum();


        return (mainCorridorPowerConsumption + subCorridorPowerConsumption) <= getPowerAllowedPerFloor(floor);
    }
}
