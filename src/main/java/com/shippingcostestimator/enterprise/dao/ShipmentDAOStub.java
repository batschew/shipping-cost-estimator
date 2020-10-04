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

    @Override
    public Shipment findShipId(int id) {
        return allShipments.get(id);
    }

    @Override
    public Shipment saveEstimate(Shipment shipment) {
        int shipmentId = shipment.getPackageId();
        allShipments.put(shipmentId, shipment);
        return shipment;
    }

    @Override
    public List<Shipment> fetchAllShipments() {
        List<Shipment> fetchShipments = new ArrayList(allShipments.values());
        return fetchShipments;
    }
}
