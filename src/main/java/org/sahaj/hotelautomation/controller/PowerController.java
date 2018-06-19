package org.sahaj.hotelautomation.controller;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.limitations.PowerConsumptionLimits;
import org.sahaj.hotelautomation.limitations.PowerLimits;
import org.sahaj.hotelautomation.models.corridors.Corridor;
import org.sahaj.hotelautomation.models.electronics.LightBulb;
import org.sahaj.hotelautomation.models.Floor;
import org.sahaj.hotelautomation.models.Motion;
import org.sahaj.hotelautomation.utils.PowerUtils;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class PowerController implements Observer {

    private Hotel hotel;
    private HashMap<Corridor, Corridor> corridorMapping;

    public PowerController(Hotel hotel) {
        this.hotel = hotel;
        this.corridorMapping = new HashMap<Corridor, Corridor>();
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public HashMap<Corridor, Corridor> getCorridorMapping() {
        return corridorMapping;
    }

    public void setCorridorMapping(HashMap<Corridor, Corridor> corridorMapping) {
        this.corridorMapping = corridorMapping;
    }

    @Override
    public void update(Observable o, Object arg) {
        Motion motion = (Motion) o;
        checkAndToggleLight(motion);
    }

    private void checkAndToggleLight(Motion motion) {

        Floor floor = hotel.getFloors().get(motion.getFloorNumber());

        Corridor subCorridor = floor.getSubCorridors().get(motion.getSubCorridorNumber());
        LightBulb light = subCorridor.getLight();

        PowerLimits limit = new PowerConsumptionLimits();

        if (!light.getStatus()) {

            if (!limit.isWithinLimit(floor)) {
                Corridor alternateSubCorridor = PowerUtils.findRandomCorridor(subCorridor, floor.getSubCorridors(), motion.getSubCorridorNumber());
                alternateSubCorridor.getAirConditioner().turnOff();
                corridorMapping.put(subCorridor, alternateSubCorridor);
            } else {
                corridorMapping.put(subCorridor, null);
            }
        }

        subCorridor.getLight().turnOn();
        light.setLastOnTime(System.currentTimeMillis());

        hotel.print();

    }
}
