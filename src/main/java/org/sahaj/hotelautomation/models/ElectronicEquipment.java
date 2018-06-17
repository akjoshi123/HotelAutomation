package org.sahaj.hotelautomation.models;

public abstract class ElectronicEquipment {

    public boolean isOn = true;
    public int powerConsumed;

    void turnOn() {

        if(!isOn)
            isOn = true;
    }

    void turnOff() {

        if(isOn)
            isOn = false;
    }

    boolean getStatus() {
        return isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public int getPowerConsumed() {
        return powerConsumed;
    }

    public void setPowerConsumed(int powerConsumed) {
        this.powerConsumed = powerConsumed;
    }
}
