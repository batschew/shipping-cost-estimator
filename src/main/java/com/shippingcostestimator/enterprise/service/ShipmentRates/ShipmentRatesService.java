package com.shippingcostestimator.enterprise.service.ShipmentRates;

import com.shippingcostestimator.enterprise.dao.ShipmentRates.IShipmentRatesDAO;
import com.shippingcostestimator.enterprise.dto.ShipmentRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentRatesService implements IShipmentRatesService{

    //Connection to the DAO
    @Autowired
    private IShipmentRatesDAO shipmentRatesSQLDAO;

    public ShipmentRatesService(IShipmentRatesDAO shipmentRatesSQLDAO){
        this.shipmentRatesSQLDAO = shipmentRatesSQLDAO;
    }

    /***
     * Saves a Shipment estimate.
     * @param shipmentRate
     * @return Saved shipmentRate estimate.
     */
    @Override
    public ShipmentRate saveRate(ShipmentRate shipmentRate) {
        return shipmentRatesSQLDAO.saveRate(shipmentRate);
    }

    /***
     * Returns a ShipmentRate object with a specified integer ID.
     * @param id
     * @return A shipmentRate based on specified id.
     */
    @Override
    public ShipmentRate findRate(int id){
        ShipmentRate uniqueId = shipmentRatesSQLDAO.findRateById(id);
        return uniqueId;
    }

    /***
     * Returns a list of all ShipmentRate objects that are currently in use by the program.
     * @return a list of all shipmentRates.
     */
    @Override
    public List<ShipmentRate> findAllRates(){
        return shipmentRatesSQLDAO.findAllRates();
    }

    /*
    * Deletes a ShipmentRate object.
     */
    @Override
    public void delete(int id) {
        shipmentRatesSQLDAO.delete(id);
    }
}
