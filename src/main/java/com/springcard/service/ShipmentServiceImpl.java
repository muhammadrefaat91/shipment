package com.springcard.service;

import com.springcard.exception.SystemException;
import com.springcard.model.Shipment;
import com.springcard.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;


    @Override
    public void createShipment(Shipment shipment) {
       Shipment existedShipment =  shipmentRepository.findByPackageDetailsSkuId(shipment.getPackageDetails().getSkuId());
       if (existedShipment != null) {
           throw new SystemException("Shipment is already existed!", SystemException.ErrorCode.NOT_UNIQUE);
       }

       shipment.setUuid(UUID.randomUUID());
       shipment.setCreationDate(LocalDateTime.now());
       shipmentRepository.save(shipment);
    }
}
