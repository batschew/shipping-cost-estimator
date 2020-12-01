package com.shippingcostestimator.enterprise.dao.ShipmentRates;

import com.shippingcostestimator.enterprise.dto.ShipmentRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShipmentRatesSQLDAO implements IShipmentRatesDAO{

    //Connection to the database.
    @Autowired
    ShipmentRatesRepository shipmentRatesRepository;

    /***
     * Saves a ShipmentRate estimate to a database.
     * @param shipmentRate
     * @return Saved shipmentRate estimate.
     */
    @Override
    public ShipmentRate saveRate(ShipmentRate shipmentRate) {
        ShipmentRate savedShipment = shipmentRatesRepository.save(shipmentRate);
        return savedShipment;
    }

    /***
     * Returns a ShipmentRate object with a specified integer ID.
     * @param id
     * @return  a specific shipment based on the id.
     */
    @Override
    public ShipmentRate findRateById(int id) {
        return shipmentRatesRepository.findById(id).get();
    }

    /***
     * Returns a list of all ShipmentRate objects that are currently in use by the program.
     * @return a list of all shipmentRates.
     */
    @Override
    public List<ShipmentRate> findAllRates() {
        List<ShipmentRate> shipmentList = new ArrayList<>();
        Iterable<ShipmentRate> iteratedList = shipmentRatesRepository.findAll();
        for(ShipmentRate shipmentRate : iteratedList){
            shipmentList.add(shipmentRate);
        }
        return shipmentList;
    }

    /*
     * Deletes a ShipmentRate object.
     */
    @Override
    public void delete(int id){
        shipmentRatesRepository.deleteById(id);
    }
}
