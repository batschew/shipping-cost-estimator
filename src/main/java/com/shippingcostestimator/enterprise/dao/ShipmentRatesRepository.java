package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.ShipmentRate;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("!test")
public interface ShipmentRatesRepository extends CrudRepository<ShipmentRate, Integer>{

}
