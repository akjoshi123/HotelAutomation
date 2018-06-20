package org.sahaj.hotelautomation.models.corridors;

import org.sahaj.hotelautomation.constants.CorridorType;

public class SubCorridor extends Corridor {

    public SubCorridor(int corridorNumber) {
        super(CorridorType.Sub);
        this.corridorNumber = corridorNumber;

        this.getLight().turnOff();
    }

    public void print() {
        System.out.print("Sub Corridor " + corridorNumber + " ");

        this.getLight().print();
        this.getAirConditioner().print();

        System.out.println();

    }
}
