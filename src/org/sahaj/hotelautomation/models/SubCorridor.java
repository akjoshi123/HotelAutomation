package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.constants.CorridorType;

public class SubCorridor extends Corridor {

    private ElectronicEquipment light, ac;
    private int powerConsumed;

    public SubCorridor(int corridorNumber) {
        type = CorridorType.Sub;
        light = new Lights();
        ac = new AirConditioner();
        number = corridorNumber;


        light.turnOff();
        ac.turnOn();
    }

    public int getPowerConsumed() {

        int consumption = 0;
        if(ac.getStatus())
            consumption = consumption + 10;
        if(light.getStatus())
            consumption = consumption + 5;

        return consumption;
    }

    public ElectronicEquipment getLight() {
        return light;
    }

    public void setLight(ElectronicEquipment light) {
        this.light = light;
    }

    public ElectronicEquipment getAc() {
        return ac;
    }

    public void setAc(ElectronicEquipment ac) {
        this.ac = ac;
    }

    public void print() {
        System.out.println("Sub Corridor " + number + " Light : " + (light.getStatus() ? "ON" : "OFF") + " : AC : " + (ac.getStatus() ? Constants.onStatus : Constants.offStatus));
    }
}
