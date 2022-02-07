package com.springcard.restcontroller;

import com.springcard.common.CarrierServiceFactory;
import com.springcard.exception.SystemException;
import com.springcard.model.CarrierServiceIDS;
import com.springcard.model.Shipment;
import com.springcard.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/shipments")
public class ShipmentRestController {

    @Autowired
    private CarrierServiceFactory carrierServiceFactory;

    public ShipmentRestController(){}

    @PostMapping("/{carrierServiceId}")
    public ResponseEntity<?> createShipment(
            @PathVariable(name = "carrierServiceId") String carrierServiceId,
            @RequestBody Shipment shipment) {
        validateParams(carrierServiceId, shipment);
        ShipmentService  shipmentService = identifyServiceType(carrierServiceId);
        shipmentService.createShipment(shipment);
        return new ResponseEntity<>("Shipment has been placed successfully", HttpStatus.CREATED);
    }

    ShipmentService identifyServiceType(String carrierServiceId) {
        return CarrierServiceFactory.getService(CarrierServiceIDS.carrierServiceIDSValue(carrierServiceId).getCarrierServiceType());
    }

    private void validateParams(String carrierServiceId, Shipment shipment) {
        CarrierServiceIDS carrierServiceIDS = CarrierServiceIDS.carrierServiceIDSValue(carrierServiceId.toLowerCase());
        if (carrierServiceIDS == null) {
            throw new SystemException("Invalid carrer service id " + carrierServiceId, SystemException.ErrorCode.NOT_VALID);
        }

        if (shipment == null || shipment.getPackageDetails() == null ||
                shipment.getPackageDetails().getSkuId() == null)
            throw new SystemException("Shipment info isn't provided!", SystemException.ErrorCode.NULL_PROPERTY);
    }


}
