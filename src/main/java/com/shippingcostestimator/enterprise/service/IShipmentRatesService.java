package com.shippingcostestimator.enterprise.service;

import com.shippingcostestimator.enterprise.dto.ShipmentRate;

import java.util.List;

public interface IShipmentRatesService {
    ShipmentRate saveRate(ShipmentRate shipmentRate);
    ShipmentRate findRate(int id);
    List<ShipmentRate> findAllRates();
    void delete(int id);
}
