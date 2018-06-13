package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.constants.CorridorType;

public class MainCorridor extends Corridor {

    private ElectronicEquipment light, ac;

    public MainCorridor(int corridorNumber) {
        type = CorridorType.Main;
        light = new Lights();
        ac = new AirConditioner();
        number = corridorNumber;

        light.turnOn();
        ac.turnOn();
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

    public int getPowerConsumed() {

        int consumption = 0;
        if(ac.getStatus())
            consumption = consumption + 10;
        if(light.getStatus())
            consumption = consumption + 5;

        return consumption;
    }

    public void setAc(ElectronicEquipment ac) {
        this.ac = ac;
    }

    public void print() {
        System.out.println("Main Corridor " + number + " Light : " + (light.getStatus() ? "ON" : "OFF") + " : AC : " + (ac.getStatus() ? Constants.onStatus : Constants.offStatus));
    }
}
