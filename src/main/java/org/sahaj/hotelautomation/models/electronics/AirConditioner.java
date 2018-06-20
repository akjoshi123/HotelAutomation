package org.sahaj.hotelautomation.models.electronics;

import org.sahaj.hotelautomation.constants.Constants;

/**
 * Extends the ElectronicEquipment class to implement AirConditioner.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class AirConditioner extends ElectronicEquipment {

    public AirConditioner(int powerConsumed) {

        this.onStatus = true;
        this.powerConsumed = powerConsumed;
    }

    public void print() {
        System.out.print("AirConditioner : " + (this.onStatus ? Constants.onStatus : Constants.offStatus) + " ");
    }

}
