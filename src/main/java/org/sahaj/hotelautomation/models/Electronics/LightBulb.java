package org.sahaj.hotelautomation.models.Electronics;

import org.sahaj.hotelautomation.constants.Constants;

public class LightBulb extends ElectronicEquipment{

    public LightBulb(int powerConsumed) {

        this.onStatus = true;
        this.powerConsumed = powerConsumed;
    }

    public void print() {
        System.out.print("Light " + (this.onStatus ? Constants.onStatus : Constants.offStatus) + " ");
    }

}
