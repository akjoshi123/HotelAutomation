package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.Constants;

import java.util.*;

public class HotelNotNeeded {

    private String hotelName;
    private HashMap<Integer, Floor> floors;

    public HotelNotNeeded(int floors, String name, int mainCorridors, int subCorridors) {

        HashMap<Integer, Floor> floorObjects = new HashMap<Integer, Floor>();

        for(int i = 1;i <= floors; i++) {
            floorObjects.put(i, new Floor(i));
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

    public HashMap<Integer, Floor> getFloors() {
        return floors;
    }

    public void setFloors(HashMap<Integer, Floor> floors) {
        this.floors = floors;
    }

    public void print() {

        Iterator<Map.Entry<Integer, Floor>> itr = floors.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<Integer, Floor> entry = itr.next();
            entry.getValue().print();
        }
    }
}
