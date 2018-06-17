package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.CorridorType;

public class LightBulb extends ElectronicEquipment{

    private int lightNumber;

    public LightBulb(int lightNumber, int powerConsumed) {

        this.isOn = true;
        this.lightNumber = lightNumber;
        this.powerConsumed = powerConsumed;
    }

    public int getLightNumber() {
        return lightNumber;
    }

    public void setLightNumber(int lightNumber) {
        this.lightNumber = lightNumber;
    }
}
