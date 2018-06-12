package org.sahaj.hotelautomation.models;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private int floorNumber;
    private List<Corridor> mainCorridors;
    private List<Corridor> subCorridors;
    private int currentPowerComsumption;

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<Corridor> getMainCorridors() {
        return mainCorridors;
    }

    public void setMainCorridors(List<Corridor> mainCorridors) {
        this.mainCorridors = mainCorridors;
    }

    public List<Corridor> getSubCorridors() {
        return subCorridors;
    }

    public void setSubCorridors(List<Corridor> subCorridors) {
        this.subCorridors = subCorridors;
    }

    public int getCurrentPowerComsumption() {
        return currentPowerComsumption;
    }

    public void setCurrentPowerComsumption(int currentPowerComsumption) {
        this.currentPowerComsumption = currentPowerComsumption;
    }

    public Floor(int floorNumber, int mainCorridor, int subCorridor) {
        this.floorNumber = floorNumber;
        mainCorridors = new ArrayList<Corridor>();
        subCorridors = new ArrayList<Corridor>();

        for(int i = 1; i <= mainCorridor; i++) {
            mainCorridors.add(new MainCorridor(i));
        }

        for(int j = 1; j <= subCorridor; j++) {
            subCorridors.add(new SubCorridor(j));
        }

    }

    public void print() {
        System.out.println("Floor " + floorNumber);

        for(Corridor mainCorridor: mainCorridors)
            mainCorridor.print();

        for(Corridor subCorridor: subCorridors)
            subCorridor.print();
    }

}
