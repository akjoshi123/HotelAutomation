package org.sahaj.hotelautomation.controller;

import org.sahaj.hotelautomation.models.Motion;



public class MotionController {

    private Motion motion;
    private PowerController powerController;

    /**
     *
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