package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("shipmentDAO")
public class ShipmentSQLDAO implements IShipmentDAO{

    @Autowired
    ShipmentRepository shipmentRepository;

    @Override
    public Shipment findShipId(int id) {
        return null;
    }

    @Override
    public Shipment saveEstimate(Shipment shipment) {
        Shipment createdShipment = shipmentRepository.save(shipment);
        return createdShipment;
    }

    @Override
    public List<Shipment> fetchAllShipments() {
        return null;
    }
}
