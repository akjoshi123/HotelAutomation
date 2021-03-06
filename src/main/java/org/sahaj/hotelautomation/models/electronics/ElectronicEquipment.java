package org.sahaj.hotelautomation.models.electronics;

/**
 * Abstract class ElectronicEquipments which is extended by Light bulb and Air conditioner.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public abstract class ElectronicEquipment {

    protected boolean onStatus = true;
    protected int powerConsumed;

    public void turnOn() {

        if (!onStatus)
            onStatus = true;
    }

    public void turnOff() {

        if (onStatus)
            onStatus = false;
    }

    public boolean getStatus() {
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
