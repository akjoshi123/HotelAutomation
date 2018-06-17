package org.sahaj.hotelautomation.models.corridors;

import org.sahaj.hotelautomation.constants.CorridorType;

public class MainCorridor extends Corridor {

    private int corridorNumber;


    public MainCorridor(int corridorNumber) {
        super(CorridorType.Main);
        this.corridorNumber = corridorNumber;
    }


    public void print() {
        System.out.println("Main Corridor " + corridorNumber);

        this.getAirConditioner().print();
        this.getLight().print();

        System.out.println();

    }
}
