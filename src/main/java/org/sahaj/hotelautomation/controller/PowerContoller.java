package org.sahaj.hotelautomation.controller;

import org.sahaj.hotelautomation.builder.Hotel;
import org.sahaj.hotelautomation.models.Motion;

import java.util.Observable;
import java.util.Observer;

public class PowerContoller implements Observer {

    private Hotel hotel;

    public PowerContoller(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void update(Observable o, Object arg) {
        Motion motion = (Motion) o;

        motion.toString();
    }
}
