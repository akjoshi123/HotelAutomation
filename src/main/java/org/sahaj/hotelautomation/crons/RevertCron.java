package org.sahaj.hotelautomation.crons;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.constants.Constants;
import org.sahaj.hotelautomation.controller.PowerController;
import org.sahaj.hotelautomation.models.corridors.Corridor;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RevertCron {

    private static ScheduledExecutorService scheduledExecutorService = Executors
            .newScheduledThreadPool(Constants.cronThreads);
    private static final long CRON_INTERVAL = Constants.cronInterval;
    private PowerController powerController;
    private Hotel hotel;

    public RevertCron(PowerController powerController, Hotel hotel) {

        this.powerController = powerController;
        this.hotel = hotel;

        runJob();
    }

    private void runJob() {

        scheduledExecutorService.scheduleAtFixedRate(() -> {

            Iterator<Map.Entry<Corridor, Corridor>> itr = this.powerController.getCorridorMapping().entrySet().iterator();
            boolean hasChanged = false;
            while (itr.hasNext()) {
                Map.Entry<Corridor, Corridor> entry = itr.next();

                Corridor corridor = entry.getKey();
                Corridor corridorAlternate = entry.getValue();


                Date lastTime = corridor.getLight().getLastOnTime();
                Date currentTime = new Date();
                long diff = currentTime.getTime() - lastTime.getTime();
                long diffMinutes = diff / (60 * 1000) % 60;

                if(diffMinutes >= Constants.lightOnInterval) {
                    hasChanged = true;

                    corridor.getLight().turnOff();
                    if(corridorAlternate != null)
                        corridorAlternate.getAirConditioner().turnOn();

                    itr.remove();
                }
            }

            if(hasChanged)
                hotel.print();

        }, 0, CRON_INTERVAL, TimeUnit.SECONDS);
    }




}
