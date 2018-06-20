package org.sahaj.hotelautomation.controller;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.limitations.PowerConsumptionLimits;
import org.sahaj.hotelautomation.limitations.PowerLimits;
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
    private HashMap<Corridor, List<Corridor>> corridorMapping;
    private List<Corridor> singleAC;

    public List<Corridor> getSingleAC() {
        return singleAC;
    }

    public void setSingleAC(List<Corridor> singleAC) {
        this.singleAC = singleAC;
    }

    public PowerController(Hotel hotel) {
        this.hotel = hotel;
        this.corridorMapping = new HashMap<Corridor, List<Corridor>>();
        this.singleAC = new ArrayList<Corridor>();

    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public HashMap<Corridor, List<Corridor>> getCorridorMapping() {
        return corridorMapping;
    }

    public void setCorridorMapping(HashMap<Corridor, List<Corridor>> corridorMapping) {
        this.corridorMapping = corridorMapping;
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

        PowerLimits limit = new PowerConsumptionLimits();

        if (!light.getStatus()) {

            if (!limit.isWithinLimit(floor)) {
                Corridor alternateSubCorridor = PowerUtils.findRandomCorridor(subCorridor, floor.getSubCorridors(), motion.getSubCorridorNumber());
                alternateSubCorridor.getAirConditioner().turnOff();

                Integer alterSubCorridorNumber = alternateSubCorridor.getCorridorNumber();

                if (!corridorMapping.containsKey(alterSubCorridorNumber)) {
                    List<Corridor> subcorridors = new ArrayList<Corridor>();
                    subcorridors.add(subCorridor);

                    corridorMapping.put(alternateSubCorridor, subcorridors);
                    singleAC.add(alternateSubCorridor);
                }
            } else {
                Corridor singleSubcorridor = singleAC.get(0);
                corridorMapping.get(singleSubcorridor).add(subCorridor);

                singleAC.remove(0);
            }
        }

        subCorridor.getLight().turnOn();
        light.setLastOnTime(System.currentTimeMillis());

        hotel.print();

    }
}
