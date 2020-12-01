package com.shippingcostestimator.enterprise.dao.ShipmentRates;

import com.easypost.model.Rate;
import com.shippingcostestimator.enterprise.dto.ShipmentRate;

import java.io.IOException;
import java.util.List;

public interface IShipmentRatesDAO {
    ShipmentRate saveRate(ShipmentRate shipmentRate);
    ShipmentRate findRateById(int id);
    List<ShipmentRate> findAllRates();
    void delete(int id);
}