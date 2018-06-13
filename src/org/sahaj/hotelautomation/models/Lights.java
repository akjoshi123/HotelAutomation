package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.Constants;

public class Lights extends ElectronicEquipment{

    private static int powerConsumed = Constants.powerConsumptionLight;

    @Override
    int getPowerConsumption() {
        return powerConsumed;
    }

    @Override
    void setPowerConsumption(int powerConsumption) {
        this.powerConsumed = powerConsumption;

    }
}
