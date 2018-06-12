package org.sahaj.hotelautomation.models;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String hotelName;
    private List<Floor> floors;

    public Hotel(int floors, String name, int mainCorridors, int subCorridors) {

        List<Floor> floorObjects = new ArrayList<Floor>();

        for(int i = 1;i <= floors; i++) {
            floorObjects.add(new Floor(i, mainCorridors, subCorridors));
        }

        this.hotelName = name;
        this.floors = floorObjects;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public void processMovement(int floor, int corridor) {


    }
}
