package org.sahaj.hotelautomation.models.Corridors;

import org.sahaj.hotelautomation.constants.CorridorType;

public class SubCorridor extends Corridor {

    private int corridorNumber;

    public SubCorridor(int corridorNumber) {
        super(CorridorType.Sub);
        this.corridorNumber = corridorNumber;

        this.getLight().turnOff();
    }

    public void print() {
        System.out.println("Sub Corridor " + corridorNumber);

        this.getAirConditioner().print();
        this.getLight().print();

        System.out.println();

    }
}
