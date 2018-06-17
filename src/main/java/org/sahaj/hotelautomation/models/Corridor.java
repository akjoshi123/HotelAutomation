package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.constants.CorridorType;

import java.util.ArrayList;
import java.util.List;

public abstract class Corridor {

    protected int number;
    protected CorridorType type;

    protected List<LightBulb> lightBulbs;
    protected List<AirConditioner> airConditioners;

    private static int powerConsumedLight = Constants.powerConsumptionLight;
    private static int powerConsumedAc = Constants.powerConsumptionAC;


    public List<LightBulb> getLights() {
        return lightBulbs;
    }

    public List<AirConditioner> getAirConditioners() {
        return airConditioners;
    }

    public Corridor(CorridorType type) {
        lightBulbs = new ArrayList<LightBulb>();
        lightBulbs.add(new LightBulb(lightBulbs.size(), powerConsumedLight));

        airConditioners = new ArrayList<AirConditioner>();
        airConditioners.add(new AirConditioner(airConditioners.size(), powerConsumedAc));

        this.type = type;

    }

    abstract void print();
}
