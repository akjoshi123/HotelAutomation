package org.sahaj.hotelautomation.models;

import java.util.Observable;

public class Motion extends Observable {

    private int floorNumber;
    private int subCorridorNumber;

    public Motion(int floorNumber, int subCorridorNumber) {
        this.floorNumber = floorNumber;
        this.subCorridorNumber = subCorridorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
        setChanged();
        notifyObservers();
    }

    public int getSubCorridorNumber() {
        return subCorridorNumber;
    }

    public void setSubCorridorNumber(int subCorridorNumber) {
        this.subCorridorNumber = subCorridorNumber;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        String s = this.floorNumber + " " + this.subCorridorNumber;
        System.out.println(s);

        return s;
    }
}
