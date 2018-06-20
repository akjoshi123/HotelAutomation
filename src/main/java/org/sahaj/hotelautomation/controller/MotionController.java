package org.sahaj.hotelautomation.controller;

import org.sahaj.hotelautomation.models.Motion;


/**
 * Manages the motion events by registering Power Controller as a observer.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class MotionController {

    private Motion motion;
    private PowerController powerController;

    /**
     * @param motion
     * @param powerController
     */
    public MotionController(Motion motion,
                            PowerController powerController) {
        this.motion = motion;
        this.powerController = powerController;
    }

    /**
     * Publishes a motion detect event which is consumed by PowerController
     */
    public void publishMotionEvent() {
        motion.addObserver(powerController);
        motion.notifyObservers();
    }
}