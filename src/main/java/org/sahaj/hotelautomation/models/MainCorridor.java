package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.constants.CorridorType;

public class MainCorridor extends Corridor {

    private int corridorNumber;


    public MainCorridor(int corridorNumber) {
        super(CorridorType.Main);
        this.corridorNumber = corridorNumber;
    }


    public void print() {
        System.out.println("Main Corridor " + corridorNumber);
    }
}
