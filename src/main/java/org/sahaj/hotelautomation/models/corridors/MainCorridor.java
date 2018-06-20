package org.sahaj.hotelautomation.models.corridors;

import org.sahaj.hotelautomation.constants.CorridorType;

public class MainCorridor extends Corridor {

    public MainCorridor(int corridorNumber) {
        super(CorridorType.Main);
        this.corridorNumber = corridorNumber;
    }


    public void print() {
        System.out.print("Main Corridor " + corridorNumber + " ");

        this.getLight().print();
        this.getAirConditioner().print();

        System.out.println();

    }
}
