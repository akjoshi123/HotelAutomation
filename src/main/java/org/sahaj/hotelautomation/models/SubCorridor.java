package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.constants.CorridorType;

public class SubCorridor extends Corridor {

    private int corridorNumber;

    public SubCorridor(int corridorNumber) {
        super(CorridorType.Sub);
        this.corridorNumber = corridorNumber;
    }
//
//    public int getPowerConsumed() {
//
//        int consumption = 0;
//        if(ac.getStatus())
//            consumption = consumption + 10;
//        if(light.getStatus())
//            consumption = consumption + 5;
//
//        return consumption;
//    }
//
//    public ElectronicEquipment getLight() {
//        return light;
//    }
//
//    public void setLight(ElectronicEquipment light) {
//        this.light = light;
//    }
//
//    public ElectronicEquipment getAc() {
//        return ac;
//    }
//
//    public void setAc(ElectronicEquipment ac) {
//        this.ac = ac;
//    }

    public void print() {
        System.out.println("Sub Corridor " + corridorNumber);
    }
}
