package org.sahaj.hotelautomation.builder;

import org.sahaj.hotelautomation.models.Corridors.Corridor;
import org.sahaj.hotelautomation.models.Corridors.MainCorridor;
import org.sahaj.hotelautomation.models.Corridors.SubCorridor;
import org.sahaj.hotelautomation.models.Floor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Hotel {

    private String hotelName;
    private HashMap<Integer, Floor> floors;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public HashMap<Integer, Floor> getFloors() {
        return floors;
    }

    public void setFloors(HashMap<Integer, Floor> floors) {
        this.floors = floors;
    }

    private Hotel(HotelBuilder builder) {

        this.floors = builder.floors;
        this.hotelName = builder.hotelName;
    }

    public void print() {

        Iterator<Map.Entry<Integer, Floor>> itr = floors.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<Integer, Floor> entry = itr.next();
            entry.getValue().print();
        }
    }

    public static class HotelBuilder {

        private String hotelName;
        private HashMap<Integer, Floor> floors;

        public HotelBuilder(String hotelName) {

            this.hotelName = hotelName;
            floors = new HashMap<Integer, Floor>();
        }


        public HotelBuilder addFloor(int floorCount) {

            for (int count = 1; count <= floorCount; count++) {
                Floor floor = new Floor(count);
                floor.setMainCorridors(new HashMap<Integer, Corridor>());
                floor.setSubCorridors(new HashMap<Integer, Corridor>());

                floors.put(count, floor);
            }

            return this;
        }

        public HotelBuilder addMainCorridor(int mainCorridors) {
            int floorCount = floors.size();

            for (int count = 1; count <= floorCount; count++) {
                for (int mainCorridor = 1; mainCorridor <= mainCorridors; mainCorridor++) {
                    MainCorridor main = new MainCorridor(mainCorridor);

                    floors.get(count).getMainCorridors().put(mainCorridor, main);
                }
            }

            return this;
        }

        public HotelBuilder addSubCorridor(int subCorridors) {
            int floorCount = floors.size();

            for (int count = 1; count <= floorCount; count++) {
                for (int subCorridor = 1; subCorridor <= subCorridors; subCorridor++) {
                    SubCorridor sub = new SubCorridor(subCorridor);

                    floors.get(count).getSubCorridors().put(subCorridor, sub);
                }
            }

            return this;
        }


        public Hotel build() {
            return new Hotel(this);
        }

    }
}
