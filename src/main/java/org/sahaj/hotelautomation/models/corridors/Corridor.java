package org.sahaj.hotelautomation.models.corridors;

import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.constants.CorridorType;
import org.sahaj.hotelautomation.models.electronics.AirConditioner;
import org.sahaj.hotelautomation.models.electronics.LightBulb;

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

        int sumOfAirConditionersPower = airConditioner.getStatus() ? airConditioner.getPowerConsumed() : 0;
        int sumOfAirLightsPower = lightBulb.getStatus() ? lightBulb.getPowerConsumed() : 0;;

        return sumOfAirConditionersPower + sumOfAirLightsPower;
    }

    public abstract void print();
}
