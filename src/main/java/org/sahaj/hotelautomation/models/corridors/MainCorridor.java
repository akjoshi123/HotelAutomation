package org.sahaj.hotelautomation.models.corridors;

import org.sahaj.hotelautomation.constants.CorridorType;

/**
 * Extends the Corridor class to implement MainCorridor.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class MainCorridor extends Corridor {

    public MainCorridor(int corridorNumber) {
        super(CorridorType.Main);
        this.corridorNumber = corridorNumber;
    }


    /**
     * Just prints the status of Light and AC for current main corridor
     *
     */
    public void print() {
        System.out.print("Main Corridor " + corridorNumber + " ");

        this.getLight().print();
        this.getAirConditioner().print();

        System.out.println();

    }
}
