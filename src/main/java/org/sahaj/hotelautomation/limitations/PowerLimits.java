package org.sahaj.hotelautomation.limitations;

import org.sahaj.hotelautomation.models.Floor;

public interface PowerLimits {

    int getPowerAllowedPerFloor(Floor floor);

    boolean isWithinLimit(Floor floor);
}
