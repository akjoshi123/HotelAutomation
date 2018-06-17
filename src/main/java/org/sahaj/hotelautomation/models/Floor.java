package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.Constants;

import java.util.*;

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

    private int getCorridorPowerConsumption(HashMap<Integer, Corridor> corridor) {
        Iterator<Map.Entry<Integer, Corridor>> itr = corridor.entrySet().iterator();

        int consumption = 0;
        while(itr.hasNext())
        {
            Map.Entry<Integer, Corridor> entry = itr.next();
            consumption = consumption + entry.getValue().getPowerConsumed();
        }

        return consumption;
    }

    public int getCurrentPowerComsumption() {

        return getCorridorPowerConsumption(mainCorridors) + getCorridorPowerConsumption(subCorridors);
    }

    public Floor(int floorNumber, int mainCorridor, int subCorridor) {
        this.floorNumber = floorNumber;
        mainCorridors = new HashMap<Integer, Corridor>();
        subCorridors = new HashMap<Integer, Corridor>();

        for(int i = 1; i <= mainCorridor; i++) {
            mainCorridors.put(i, new MainCorridor(i));
        }

        for(int j = 1; j <= subCorridor; j++) {
            subCorridors.put(j, new SubCorridor(j));
        }

        setMaxAllowedPowerConsumption(mainCorridor * Constants.powerConsumptionAllowedMaincorridor + subCorridor * Constants.powerConsumptionAllowedSubcorridor);

    }

    private void printCorridor(HashMap<Integer, Corridor> corridor) {
        Iterator<Map.Entry<Integer, Corridor>> itr = corridor.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<Integer, Corridor> entry = itr.next();
            entry.getValue().print();
        }
    }

    public boolean checkLightStatus(int corridorNumber) {

        if(subCorridors.containsKey(corridorNumber)) {
            Corridor corridor = subCorridors.get(corridorNumber);

            return corridor.getLight().getStatus();
        } else
            System.out.println("No such corridor present!!!");

        return false;
    }

    public void turnOnLight(int corridorNumber) {
        if(subCorridors.containsKey(corridorNumber)) {
            Corridor corridor = subCorridors.get(corridorNumber);

            corridor.getLight().turnOn();
        } else
            System.out.println("No such corridor present!!!");
    }

    public void turnOffLight(int corridorNumber) {
        if(subCorridors.containsKey(corridorNumber)) {
            Corridor corridor = subCorridors.get(corridorNumber);

            corridor.getLight().turnOff();
        } else
            System.out.println("No such corridor present!!!");
    }

    public void print() {
        System.out.println("Floor " + floorNumber);

        printCorridor(mainCorridors);

        printCorridor(subCorridors);

    }

    public boolean switchOffRandomFloorAC(int corridorNumber) {

        Iterator<Map.Entry<Integer, Corridor>> itr = subCorridors.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<Integer, Corridor> entry = itr.next();
            Corridor corridor = entry.getValue();

            if(entry.getKey() == corridorNumber)
                continue;

            if(!corridor.getAirConditioner().getStatus())
                continue;

            corridor.getAirConditioner().turnOff();

            return true;
        }

        return false;
    }
}
