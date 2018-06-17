package org.sahaj.hotelautomation.models.Electronics;

public abstract class ElectronicEquipment {

    protected boolean onStatus = true;
    protected int powerConsumed;

    public void turnOn() {

        if(!onStatus)
            onStatus = true;
    }

    public void turnOff() {

        if(onStatus)
            onStatus = false;
    }

    public boolean getStatus() {
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

    public abstract void print();
}
