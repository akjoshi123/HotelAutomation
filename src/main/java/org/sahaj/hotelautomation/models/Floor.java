package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.models.corridors.Corridor;

import java.util.*;

/**
 * Floor entity which stores the relation between floor and its corridors.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class Floor {

    private int floorNumber;
    private HashMap<Integer, Corridor> mainCorridors;
    private HashMap<Integer, Corridor> subCorridors;
    private int maxAllowedPowerConsumption;

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public HashMap<Integer, Corridor> getMainCorridors() {
        return mainCorridors;
    }

    public void setMainCorridors(HashMap<Integer, Corridor> mainCorridors) {
        this.mainCorridors = mainCorridors;
    }

    public HashMap<Integer, Corridor> getSubCorridors() {
        return subCorridors;
    }

    public void setSubCorridors(HashMap<Integer, Corridor> subCorridors) {
        this.subCorridors = subCorridors;
    }

    public void setMaxAllowedPowerConsumption(int maxAllowedPowerConsumption) {
        this.maxAllowedPowerConsumption = maxAllowedPowerConsumption;
    }

    public int getMaxAllowedPowerConsumption() {
        return maxAllowedPowerConsumption;
    }

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    private void printCorridor(HashMap<Integer, Corridor> corridor) {
        Iterator<Map.Entry<Integer, Corridor>> itr = corridor.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<Integer, Corridor> entry = itr.next();
            entry.getValue().print();
        }
    }

    /**
     * Just prints all the details of Floor.
     *
     */
    public void print() {
        String space = String.format("%"+ 20 +"s", " ");
        System.out.printf(space + "Floor " + floorNumber);
        System.out.println();

        printCorridor(mainCorridors);

        printCorridor(subCorridors);

        System.out.println();

    }
}
