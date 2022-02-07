package com.springcard.common;


import com.springcard.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarrierServiceFactory {

    @Autowired
    private List<ShipmentService> shipmentServices;

    private static final Map<CarrierServiceType, ShipmentService> myServiceCache = new HashMap<>();

    @PostConstruct
    public void initMyServiceCache() {
        for(ShipmentService shipmentService : shipmentServices) {
            myServiceCache.put(shipmentService.getType(), shipmentService);
        }
    }

    public static ShipmentService getService(CarrierServiceType type) {
        ShipmentService service = myServiceCache.get(type);
        if(service == null) throw new RuntimeException("Unknown service type: " + type);
        return service;
    }
}
