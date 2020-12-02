package com.shippingcostestimator.enterprise.service;

import com.shippingcostestimator.enterprise.dao.IShipmentRatesDAO;
import com.shippingcostestimator.enterprise.dto.ShipmentRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentRatesService implements IShipmentRatesService{

    //Connection to the DAO
    @Autowired
    private IShipmentRatesDAO shipmentRatesDAO;

    public ShipmentRatesService(){

    }

    public ShipmentRatesService(IShipmentRatesDAO shipmentRatesDAO){
        this.shipmentRatesDAO = shipmentRatesDAO;
    }

    /***
     * Saves a Shipment estimate.
     * @param shipmentRate
     * @return Saved shipmentRate estimate.
     */
    @Override
    public ShipmentRate saveRate(ShipmentRate shipmentRate) {
        return shipmentRatesDAO.saveRate(shipmentRate);
    }

    /***
     * Returns a ShipmentRate object with a specified integer ID.
     * @param id
     * @return A shipmentRate based on specified id.
     */
    @Override
    @Cacheable(value="shipmentRate", key="#id")
    public ShipmentRate findRate(int id){
        ShipmentRate uniqueId = shipmentRatesDAO.findRateById(id);
        return uniqueId;
    }

    /***
     * Returns a list of all ShipmentRate objects that are currently in use by the program.
     * @return a list of all shipmentRates.
     */
    @Override
    @Cacheable("shipmentRate")
    public List<ShipmentRate> findAllRates(){
        return shipmentRatesDAO.findAllRates();
    }

    /*
    * Deletes a ShipmentRate object.
     */
    @Override
    @CacheEvict(value="shipmentRate", key="#id")
    public void delete(int id) {
        shipmentRatesDAO.delete(id);
    }
}
