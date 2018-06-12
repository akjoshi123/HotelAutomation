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
        System.out.println("Main Corridor " + number + " Light : " + (light.getStatus() ? "ON" : "OFF") + " : AC : " + (ac.getStatus() ? Constants.onStatus : Constants.offStatus));
    }
}
