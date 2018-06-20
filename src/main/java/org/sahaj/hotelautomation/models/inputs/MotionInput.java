package org.sahaj.hotelautomation.models.inputs;

/**
 * Entity to store motion details.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public class MotionInput {

    private int floor, subCorridor;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getSubCorridor() {
        return subCorridor;
    }

    public void setSubCorridor(int subCorridor) {
        this.subCorridor = subCorridor;
    }

    public MotionInput(int floor, int subCorridor) {
        this.floor = floor;
        this.subCorridor = subCorridor;
    }
}
