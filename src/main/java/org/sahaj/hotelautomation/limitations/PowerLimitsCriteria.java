package org.sahaj.hotelautomation.limitations;

import org.sahaj.hotelautomation.models.Floor;

/**
 * Interface which needs to be implemented as power limits criteria.
 *
 * @author akjoshi on 19/06/18
 * @project HotelAutomation
 */
public interface PowerLimitsCriteria {

    int getPowerAllowedPerFloor(Floor floor);

    boolean isWithinLimit(Floor floor);

    boolean canACBeTurnedON(Floor floor);
}
