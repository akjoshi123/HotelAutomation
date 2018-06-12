package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.CorridorType;

public class SubCorridor extends Corridor {

    private ElectronicEquipment light, ac;

    public SubCorridor(int corridorNumber) {
        type = CorridorType.Sub;
        light = new Lights();
        ac = new AirConditioner();
        number = corridorNumber;
    }

    public void printStatus() {
        System.out.print("Sub Corridor " + number + " Light : " + light.getStatus() + " : AC : " + ac.getStatus());
    }
}
