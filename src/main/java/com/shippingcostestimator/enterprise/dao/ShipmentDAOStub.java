package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.Shipment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShipmentDAOStub implements IShipmentDAO{

    //implementation of the Hashmaps described on week 5
    Map<Integer, Shipment> allShipments = new HashMap<>();

    @Override
    public Shipment findShipId(int id) {
        return allShipments.get(id);
    }

    @Override
    public Shipment saveEstimate(Shipment shipment) {
        //Integer shipmentId = Integer.parseInt(shipment.getShipmentId());
        return null;
    }

    @Override
    public List<Shipment> fetchAllShipments() {
        return null;
    }
}
