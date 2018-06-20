package org.sahaj.hotelautomation.crons;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.controller.PowerController;
import org.sahaj.hotelautomation.limitations.PowerConsumptionLimitsCriteria;
import org.sahaj.hotelautomation.limitations.PowerLimitsCriteria;
import org.sahaj.hotelautomation.models.Floor;
import org.sahaj.hotelautomation.models.corridors.Corridor;
import org.sahaj.hotelautomation.utils.PowerUtils;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Cron which will revert all the changes done when light bulb was turned ON.
 * This cron will be triggered every 5 seconds to check if a light bulb of
 * sub corridor is ON for more than a minute.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class RestoreCron {

    private static ScheduledExecutorService scheduledExecutorService = Executors
            .newScheduledThreadPool(Constants.cronThreads);
    private static final long CRON_INTERVAL = Constants.cronIntervalSeconds;
    private PowerController powerController;
    private Hotel hotel;

    public RestoreCron(PowerController powerController, Hotel hotel) {

        this.powerController = powerController;
        this.hotel = hotel;

        runJob();
    }

    /**
     * This is a executor will turns ON the AC for the Light Bulb which is ON for more than a minute.
     * In current configuration, turning of a AC OFF will allow for 2 light bulbs to turn ON, so the cron
     * will make sure that while reverting the AC will be turned ON only when both the light bulbs are OFF.
     */
    private void runJob() {

        scheduledExecutorService.scheduleAtFixedRate(() -> {

            Iterator<Map.Entry<Corridor, Floor>> itr = this.powerController.getLightOnCorridors().entrySet().iterator();

            boolean hasChanged = false;
            while (itr.hasNext()) {
                Map.Entry<Corridor, Floor> entry = itr.next();

                Corridor corridor = entry.getKey();
                Floor floor = entry.getValue();

                Date lastTime = corridor.getLight().getLastOnTime();
                Date currentTime = new Date();
                long diff = currentTime.getTime() - lastTime.getTime();
                long diffMinutes = diff / (60 * 1000) % 60;

                if (diffMinutes >= Constants.lightOnIntervalMinutes) {
                    hasChanged = true;

                    corridor.getLight().turnOff();

                    PowerLimitsCriteria powerLimitsCriteria = PowerConsumptionLimitsCriteria.getInstance();
                    if (powerLimitsCriteria.canACBeTurnedON(floor)) {
                        PowerUtils.turnONRandomAC(floor.getSubCorridors());
                    }
                    itr.remove();
                }
            }

            if (hasChanged)
                hotel.print();

        }, 0, CRON_INTERVAL, TimeUnit.SECONDS);
    }


}
