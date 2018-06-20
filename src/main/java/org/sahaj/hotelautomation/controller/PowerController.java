package org.sahaj.hotelautomation.controller;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.limitations.PowerConsumptionLimitsCriteria;
import org.sahaj.hotelautomation.limitations.PowerLimitsCriteria;
import org.sahaj.hotelautomation.models.corridors.Corridor;
import org.sahaj.hotelautomation.models.electronics.LightBulb;
import org.sahaj.hotelautomation.models.Floor;
import org.sahaj.hotelautomation.models.Motion;
import org.sahaj.hotelautomation.utils.PowerUtils;

import java.util.*;

/**
 * Observes the motion change event and toggles the status of AC and Light bulb accordingly.
 * Also keeps track of relation between AC which was switched OFF for a Light bulb to turn on.
 * Heart of the application.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class PowerController implements Observer {

    private Hotel hotel;
    private HashMap<Corridor, Floor> lightOnCorridors;

    public PowerController(Hotel hotel) {
        this.hotel = hotel;
        this.lightOnCorridors = new HashMap<Corridor, Floor>();
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public HashMap<Corridor, Floor> getLightOnCorridors() {
        return lightOnCorridors;
    }

    public void setLightOnCorridors(HashMap<Corridor, Floor> lightOnCorridors) {
        this.lightOnCorridors = lightOnCorridors;
    }

    @Override
    public void update(Observable o, Object arg) {
        Motion motion = (Motion) o;
        checkAndToggleLight(motion);
    }

    /**
     * Toggles the AC to OFF state if the power consumption is going beyond the maximum allowed.
     * Keeps track of the activity, this data is then used by restore cron to revert all the changes.
     *
     * @param motion
     */
    private void checkAndToggleLight(Motion motion) {

        Floor floor = hotel.getFloors().get(motion.getFloorNumber());

        Corridor subCorridor = floor.getSubCorridors().get(motion.getSubCorridorNumber());
        LightBulb light = subCorridor.getLight();

        PowerLimitsCriteria limit = PowerConsumptionLimitsCriteria.getInstance();

        if (!light.getStatus()) {

            if (!limit.isWithinLimit(floor)) {
                Corridor alternateSubCorridor = PowerUtils.findRandomCorridor(subCorridor, floor.getSubCorridors(), motion.getSubCorridorNumber());
                alternateSubCorridor.getAirConditioner().turnOff();

            }
            lightOnCorridors.put(subCorridor, floor);
        }

        subCorridor.getLight().turnOn();

        //If motion is detected in same sub corridor within 1 minute of previous motion, then we update the timestamp
        //to extend the light ON interval by another 1 minute.
        light.setLastOnTime(System.currentTimeMillis());

        hotel.print();

    }
}
