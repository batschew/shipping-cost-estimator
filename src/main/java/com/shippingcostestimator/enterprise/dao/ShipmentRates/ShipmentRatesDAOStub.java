package com.shippingcostestimator.enterprise.dao.ShipmentRates;

import com.shippingcostestimator.enterprise.dto.ShipmentRate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("test")
public class ShipmentRatesDAOStub implements IShipmentRatesDAO{

    Map<Integer, ShipmentRate> allShipmentRates = new HashMap<>();

    /***
     * Saves a ShipmentRate estimate to a hashmap.
     * @param shipmentRate
     * @return Saved shipmentRate estimate.
     */
    @Override
    public ShipmentRate saveRate(ShipmentRate shipmentRate) {
        int shipmentId = shipmentRate.getId();
        allShipmentRates.put(shipmentId, shipmentRate);
        return shipmentRate;
    }

    /***
     * Returns a ShipmentRate object with a specified integer ID.
     * @param id
     * @return  a specific shipment based on the id.
     */
    @Override
    public ShipmentRate findRateById(int id) {
        return allShipmentRates.get(id);
    }

    /***
     * Returns a list of all ShipmentRate objects that are currently in use by the program.
     * @return a list of all shipmentRates.
     */
    @Override
    public List<ShipmentRate> findAllRates() {
        List<ShipmentRate> fetchShipmentRates = new ArrayList(allShipmentRates.values());
        return fetchShipmentRates;
    }

    /*
     * Deletes a ShipmentRate object.
     */
    @Override
    public void delete(int id) {
        allShipmentRates.remove(id);
    }
}
