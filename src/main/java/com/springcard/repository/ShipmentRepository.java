package com.springcard.repository;

import com.springcard.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID>{
    Shipment findByPackageDetailsSkuId(String skuId);
}
