package org.sahaj.hotelautomation.models;

import java.util.Observable;

/**
 * Motion entity which uses Observer pattern to send update of movement to PowerController.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class Motion extends Observable {

    private int floorNumber;
    private int subCorridorNumber;

    public Motion(int floorNumber, int subCorridorNumber) {
        this.floorNumber = floorNumber;
        this.subCorridorNumber = subCorridorNumber;

        setChanged();
        notifyObservers();
    }

    public Motion() {

    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    /**
     * Will generate motion update event which will be consumed by PowerController.
     *
     * @param object
     */
    @Override
    public void notifyObservers(Object object) {
        setChanged();
        super.notifyObservers(object);
    }

    public int getSubCorridorNumber() {
        return subCorridorNumber;
    }

    public void setSubCorridorNumber(int subCorridorNumber) {
        this.subCorridorNumber = subCorridorNumber;
    }

    @Override
    public String toString() {
        String s = this.floorNumber + " " + this.subCorridorNumber;
        System.out.println(s);

        return s;
    }
}
