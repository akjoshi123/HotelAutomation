package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.constants.CorridorType;

public abstract class Corridor {

    protected CorridorType type;

    protected LightBulb lightBulb;
    protected AirConditioner airConditioner;

    private static int powerConsumedLight = Constants.powerConsumptionLight;
    private static int powerConsumedAc = Constants.powerConsumptionAC;


    public LightBulb getLight() {
        return lightBulb;
    }

    public AirConditioner getAirConditioner() {
        return airConditioner;
    }

    public Corridor(CorridorType type) {
        lightBulb = new LightBulb( powerConsumedLight);

        airConditioner = new AirConditioner(powerConsumedAc);

        this.type = type;

    }

    public int getPowerConsumed() {

        AirConditioner airConditioner = this.getAirConditioner();
        LightBulb lightBulb = this.getLight();

        int sumOfAirConditionersPower = airConditioner.getOnStatus() ? airConditioner.getPowerConsumed() : 0;
        int sumOfAirLightsPower = lightBulb.getOnStatus() ? lightBulb.getPowerConsumed() : 0;;

        return sumOfAirConditionersPower + sumOfAirLightsPower;
    }

    abstract void print();
}
