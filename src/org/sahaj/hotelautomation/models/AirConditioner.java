package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.Constants;

public class AirConditioner extends ElectronicEquipment{

    private static int powerConsumed = Constants.powerConsumptionAC;

    @Override
    int getPowerConsumption() {
        return powerConsumed;
    }

    @Override
    void setPowerConsumption(int powerConsumption) {
        this.powerConsumed = powerConsumption;

    }
}
