package com.springcard.service;

import com.springcard.common.CarrierServiceType;
import com.springcard.exception.SystemException;
import com.springcard.model.Shipment;
import com.springcard.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@Qualifier("fedexShipmentService")
public class FedexShipmentServiceImpl implements ShipmentService {

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

    @Override
    public CarrierServiceType getType() {
        return CarrierServiceType.FEDEX;
    }
}
