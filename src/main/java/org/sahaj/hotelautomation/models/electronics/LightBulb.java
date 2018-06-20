package org.sahaj.hotelautomation.models.electronics;

import org.sahaj.hotelautomation.constants.Constants;

import java.util.Date;

/**
 * Extends the ElectronicEquipment class to implement LightBulb.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class LightBulb extends ElectronicEquipment {

    private Date lastOnTime;

    public Date getLastOnTime() {
        return lastOnTime;
    }

    public void setLastOnTime(long lastOnTime) {
        this.lastOnTime = new Date(lastOnTime);
    }

    public LightBulb(int powerConsumed) {

        this.onStatus = true;
        this.powerConsumed = powerConsumed;
    }

    public void print() {
        System.out.print("Light : " + (this.onStatus ? Constants.onStatus : Constants.offStatus) + " ");
    }

}
