package org.sahaj.hotelautomation.models;

import org.sahaj.hotelautomation.constants.CorridorType;

public abstract class Corridor {

    protected int number;
    protected CorridorType type;

    protected ElectronicEquipment light, ac;

    abstract void setLight(ElectronicEquipment light);
    abstract ElectronicEquipment getLight();

    abstract void setAc(ElectronicEquipment ac);
    abstract ElectronicEquipment getAc();

    abstract void print();
    abstract int getPowerConsumed();
}
