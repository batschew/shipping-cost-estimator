package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.Shipment;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("!test")
public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {
}
