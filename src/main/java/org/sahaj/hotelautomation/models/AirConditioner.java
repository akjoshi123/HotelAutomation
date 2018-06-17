package org.sahaj.hotelautomation.models;

public class AirConditioner extends ElectronicEquipment{

    private int acNumber;

    public AirConditioner(int acNumber, int powerConsumed) {

        this.isOn = true;
        this.acNumber = acNumber;
        this.powerConsumed = powerConsumed;
    }

    public int getAcNumber() {
        return acNumber;
    }

    public void setAcNumber(int acNumber) {
        this.acNumber = acNumber;
    }
}
