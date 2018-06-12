package org.sahaj.hotelautomation.models;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private int floorNumber;
    private List<Corridor> mainCorridors;
    private List<Corridor> subCorridors;

    public Floor(int floorNumber, int mainCorridor, int subCorridor) {
        this.floorNumber = floorNumber;
        mainCorridors = new ArrayList<Corridor>();
        subCorridors = new ArrayList<Corridor>();

        for(int i = 1; i <= mainCorridor; i++) {
            mainCorridors.add(new MainCorridor(i));
        }

        for(int j = 1; j <= subCorridor; j++) {
            subCorridors.add(new SubCorridor(j));
        }

    }

}
