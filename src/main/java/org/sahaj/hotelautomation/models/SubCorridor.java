package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.constants.CorridorType;

public class SubCorridor extends Corridor {

    private int corridorNumber;

    public SubCorridor(int corridorNumber) {
        super(CorridorType.Sub);
        this.corridorNumber = corridorNumber;
    }

    public void print() {
        System.out.println("Sub Corridor " + corridorNumber);
    }
}
