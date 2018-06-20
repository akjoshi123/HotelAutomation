package org.sahaj.hotelautomation.models.corridors;

import org.sahaj.hotelautomation.constants.CorridorType;

/**
 * Extends the Corridor class to implement SubCorridor.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class SubCorridor extends Corridor {

    public SubCorridor(int corridorNumber) {
        super(CorridorType.Sub);
        this.corridorNumber = corridorNumber;

        this.getLight().turnOff();
    }

    /**
     * Just prints the status of Light and AC for current sub corridor
     *
     */
    public void print() {
        System.out.print("Sub Corridor " + corridorNumber + " ");

        this.getLight().print();
        this.getAirConditioner().print();

        System.out.println();

    }
}
