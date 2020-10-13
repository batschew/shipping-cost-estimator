package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.Shipment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShipmentDAOStub implements IShipmentDAO{

    //implementation of the Hashmaps described on week 5
    Map<Integer, Shipment> allShipments = new HashMap<>();

    /***
     * Returns a Shipment object with a specified integer ID.
     * @param id
     * @return  a specific shipment based on shipmentId.
     */
    @Override
    public Shipment findShipId(int id) {
        return allShipments.get(id);
    }

    /***
     * Saves a Shipment estimate.
     * @param shipment
     * @return Saved shipment estimate.
     */
    @Override
    public Shipment saveEstimate(Shipment shipment) {
        int shipmentId = shipment.getPackageId();
        allShipments.put(shipmentId, shipment);
        return shipment;
    }

    /***
     * Returns a list of Shipment objects that are currently in use by the program.
     * @return A list of shipments.
     */
    @Override
    public List<Shipment> fetchAllShipments() {
        List<Shipment> fetchShipments = new ArrayList(allShipments.values());
        return fetchShipments;
    }
}
