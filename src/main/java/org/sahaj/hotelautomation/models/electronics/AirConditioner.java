package org.sahaj.hotelautomation.models.electronics;

import org.sahaj.hotelautomation.constants.Constants;

public class AirConditioner extends ElectronicEquipment {

    public AirConditioner(int powerConsumed) {

        this.onStatus = true;
        this.powerConsumed = powerConsumed;
    }

    public void print() {
        System.out.print("AirConditioner : " + (this.onStatus ? Constants.onStatus : Constants.offStatus) + " ");
    }

}
