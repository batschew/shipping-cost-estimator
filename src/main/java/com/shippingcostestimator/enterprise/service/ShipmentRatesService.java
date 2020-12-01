package com.shippingcostestimator.enterprise.service;

import com.shippingcostestimator.enterprise.dao.ShipmentRates.IShipmentRatesDAO;
import com.shippingcostestimator.enterprise.dto.ShipmentRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentRatesService implements IShipmentRatesService{

    @Autowired
    private IShipmentRatesDAO shipmentRatesSQLDAO;

    public ShipmentRatesService(IShipmentRatesDAO shipmentRatesSQLDAO){
        this.shipmentRatesSQLDAO = shipmentRatesSQLDAO;
    }

    @Override
    public ShipmentRate saveRate(ShipmentRate shipmentRate) {
        return shipmentRatesSQLDAO.saveRate(shipmentRate);
    }
}
