package org.sahaj.hotelautomation.models.inputs;

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
