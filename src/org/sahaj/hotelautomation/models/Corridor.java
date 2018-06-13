package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.CorridorType;

public abstract class Corridor {

    protected int number;
    protected CorridorType type;

    abstract void print();
    abstract int getPowerConsumed();
}
