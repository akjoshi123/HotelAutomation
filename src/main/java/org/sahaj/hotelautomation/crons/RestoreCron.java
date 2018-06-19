package org.sahaj.hotelautomation.crons;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.controller.PowerController;
import org.sahaj.hotelautomation.models.corridors.Corridor;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    private void runJob() {

        scheduledExecutorService.scheduleAtFixedRate(() -> {

            Iterator<Map.Entry<Corridor, List<Corridor>>> itr = this.powerController.getCorridorMapping().entrySet().iterator();

            boolean hasChanged = false;
            while (itr.hasNext()) {
                Map.Entry<Corridor, List<Corridor>> entry = itr.next();

                Corridor corridorAlternate = entry.getKey();
                List<Corridor> corridors = entry.getValue();

                for (Iterator<Corridor> iterator = corridors.iterator(); iterator.hasNext();) {
                    Corridor corridor = iterator.next();
                    Date lastTime = corridor.getLight().getLastOnTime();
                    Date currentTime = new Date();
                    long diff = currentTime.getTime() - lastTime.getTime();
                    long diffMinutes = diff / (60 * 1000) % 60;

                    if (diffMinutes >= Constants.lightOnIntervalMinutes) {
                        hasChanged = true;

                        corridor.getLight().turnOff();


                        if (corridors.size() > 1) {
                            iterator.remove();
                        } else {
                            corridorAlternate.getAirConditioner().turnOn();
                            itr.remove();

                        }

                    }
                }
            }

            if (hasChanged)
                hotel.print();

        }, 0, CRON_INTERVAL, TimeUnit.SECONDS);
    }


}
