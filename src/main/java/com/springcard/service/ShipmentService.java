package com.springcard.service;

import com.springcard.common.CarrierServiceType;
import com.springcard.model.Shipment;

public interface ShipmentService {

    void createShipment(Shipment shipment);

    CarrierServiceType getType();
}
