package org.sahaj.hotelautomation.models;

public abstract class ElectronicEquipment {

    protected boolean onStatus = true;
    protected int powerConsumed;

    void turnOn() {

        if(!onStatus)
            onStatus = true;
    }

    void turnOff() {

        if(onStatus)
            onStatus = false;
    }

    boolean getStatus() {
        return onStatus;
    }

    public boolean getOnStatus() {
        return onStatus;
    }

    public void setOnStatus(boolean onStatus) {
        this.onStatus = onStatus;
    }

    public int getPowerConsumed() {
        return powerConsumed;
    }

    public void setPowerConsumed(int powerConsumed) {
        this.powerConsumed = powerConsumed;
    }
}
