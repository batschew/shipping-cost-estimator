package com.shippingcostestimator.enterprise.service;

import com.shippingcostestimator.enterprise.dto.ShipmentRate;

public interface IShipmentRatesService {
    ShipmentRate saveRate(ShipmentRate shipmentRate);
}
