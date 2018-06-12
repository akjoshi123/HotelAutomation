package org.sahaj.hotelautomation.models;

public abstract class ElectronicEquipment {

    private boolean isOn = true;

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
}
