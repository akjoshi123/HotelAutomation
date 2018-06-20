package org.sahaj.hotelautomation.limitations;

import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.models.corridors.Corridor;
import org.sahaj.hotelautomation.models.Floor;

import java.util.HashMap;

/**
 * Implements the Power Limits interface
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class PowerConsumptionLimits implements PowerLimits {

    private static PowerConsumptionLimits powerConsumptionLimits;

    public static PowerConsumptionLimits getInstance() {
        if (powerConsumptionLimits == null) {
            powerConsumptionLimits = new PowerConsumptionLimits();
        }
        return powerConsumptionLimits;
    }

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


        return (mainCorridorPowerConsumption + subCorridorPowerConsumption + Constants.powerConsumptionLight) <= getPowerAllowedPerFloor(floor);
    }

    @Override
    public boolean canACBeTurnedON(Floor floor) {
        HashMap<Integer, Corridor> mainCorridors = floor.getMainCorridors();
        HashMap<Integer, Corridor> subCorridors = floor.getSubCorridors();

        int mainCorridorPowerConsumption = mainCorridors.entrySet().stream().mapToInt(x -> x.getValue().getPowerConsumed()).sum();
        int subCorridorPowerConsumption = subCorridors.entrySet().stream().mapToInt(x -> x.getValue().getPowerConsumed()).sum();

        return (mainCorridorPowerConsumption + subCorridorPowerConsumption + Constants.powerConsumptionAC <= getPowerAllowedPerFloor(floor));
    }
}
