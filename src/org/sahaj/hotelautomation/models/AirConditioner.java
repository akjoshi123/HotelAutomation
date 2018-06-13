package org.sahaj.hotelautomation.models;

public class AirConditioner extends ElectronicEquipment{

    private static int powerConsumed = 10;

    public static int getPowerConsumed() {
        return powerConsumed;
    }

    public static void setPowerConsumed(int powerConsumed) {
        AirConditioner.powerConsumed = powerConsumed;
    }
}
