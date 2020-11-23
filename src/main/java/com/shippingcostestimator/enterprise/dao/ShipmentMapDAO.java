package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.ShipmentMap;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShipmentMapDAO implements IShipmentMapDAO{

    //implementation of the Hashmaps described on week 5
    Map<Integer, ShipmentMap> allShipments = new HashMap<>();

    /***
     * Returns a Shipment object with a specified integer ID.
     * @param id
     * @return  a specific shipment based on shipmentId.
     */
    @Override
    public ShipmentMap findShipById(int id) {
        return allShipments.get(id);
    }

    /***
     * Saves a Shipment estimate.
     * @param shipmentMap
     * @return Saved shipment estimate.
     */
    @Override
    public ShipmentMap saveEstimate(ShipmentMap shipmentMap) {
        int shipmentId = shipmentMap.getId();
        allShipments.put(shipmentId, shipmentMap);
        return shipmentMap;
    }

    /***
     * Returns a list of Shipment objects that are currently in use by the program.
     * @return A list of shipments.
     */
    @Override
    public List<ShipmentMap> fetchAllShipments() {
        List<ShipmentMap> fetchShipments = new ArrayList(allShipments.values());
        return fetchShipments;
    }
}
