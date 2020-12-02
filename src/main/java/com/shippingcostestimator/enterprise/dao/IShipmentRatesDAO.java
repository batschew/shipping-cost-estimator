package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.ShipmentRate;

import java.util.List;

public interface IShipmentRatesDAO {
    ShipmentRate saveRate(ShipmentRate shipmentRate);
    ShipmentRate findRateById(int id);
    List<ShipmentRate> findAllRates();
    void delete(int id);
}
