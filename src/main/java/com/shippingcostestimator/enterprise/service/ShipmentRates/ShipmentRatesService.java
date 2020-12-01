package com.shippingcostestimator.enterprise.service.ShipmentRates;

import com.shippingcostestimator.enterprise.dao.ShipmentRates.IShipmentRatesDAO;
import com.shippingcostestimator.enterprise.dto.ShipmentRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ShipmentRate findRate(int id){
        ShipmentRate uniqueId = shipmentRatesSQLDAO.findRateById(id);
        return uniqueId;
    }

    @Override
    public List<ShipmentRate> findAllRates(){
        return shipmentRatesSQLDAO.findAllRates();
    }

    @Override
    public void delete(int id) {
        shipmentRatesSQLDAO.delete(id);
    }
}
