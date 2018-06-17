package org.sahaj.hotelautomation.models;

public class AirConditioner extends ElectronicEquipment{

    public AirConditioner(int powerConsumed) {

        this.onStatus = true;
        this.powerConsumed = powerConsumed;
    }

}
