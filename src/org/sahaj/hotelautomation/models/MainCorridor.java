package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.CorridorType;

public class MainCorridor extends Corridor {

    private ElectronicEquipment light, ac;

    public MainCorridor(int corridorNumber) {
        type = CorridorType.Main;
        light = new Lights();
        ac = new AirConditioner();
        number = corridorNumber;
    }

    public void printStatus() {
        System.out.print("Main Corridor " + number + " Light : " + light.getStatus() + " : AC : " + ac.getStatus());
    }
}
