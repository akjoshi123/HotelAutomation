package org.sahaj.hotelautomation.models.corridors;

import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.constants.CorridorType;
import org.sahaj.hotelautomation.models.electronics.AirConditioner;
import org.sahaj.hotelautomation.models.electronics.LightBulb;

/**
 * Abstract class Corridor which is extended by Main corridor and Sub corridor.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public abstract class Corridor {

    protected CorridorType type;
    protected int corridorNumber;

    public int getCorridorNumber() {
        return corridorNumber;
    }

    public void setCorridorNumber(int corridorNumber) {
        this.corridorNumber = corridorNumber;
    }

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
        lightBulb = new LightBulb(powerConsumedLight);

        airConditioner = new AirConditioner(powerConsumedAc);

        this.type = type;

    }

    /**
     * Returns the power consumed by the current corridor.
     *
     * @return
     */
    public int getPowerConsumed() {

        AirConditioner airConditioner = this.getAirConditioner();
        LightBulb lightBulb = this.getLight();

        int sumOfAirConditionersPower = airConditioner.getStatus() ? airConditioner.getPowerConsumed() : 0;
        int sumOfAirLightsPower = lightBulb.getStatus() ? lightBulb.getPowerConsumed() : 0;

        return sumOfAirConditionersPower + sumOfAirLightsPower;
    }

    public abstract void print();
}
