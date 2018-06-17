package org.sahaj.hotelautomation.models.inputs;

public class InitialInput {
    private int floor, mainCorridorCount, subCorridorCount;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getMainCorridorCount() {
        return mainCorridorCount;
    }

    public void setMainCorridorCount(int mainCorridorCount) {
        this.mainCorridorCount = mainCorridorCount;
    }

    public int getSubCorridorCount() {
        return subCorridorCount;
    }

    public void setSubCorridorCount(int subCorridorCount) {
        this.subCorridorCount = subCorridorCount;
    }

    public InitialInput(int floorCount, int mainCorridorCount, int subCorridorCount) {
        this.floor = floorCount;
        this.mainCorridorCount = mainCorridorCount;
        this.subCorridorCount = subCorridorCount;
    }
}
