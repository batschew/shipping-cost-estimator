package com.shippingcostestimator.enterprise.service;



import com.shippingcostestimator.enterprise.dao.IShipmentDAO;
import com.shippingcostestimator.enterprise.dto.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Stub that implements the IShippingCostService interface.
 */

@Service
public class ShipmentServiceStub implements IShipmentService {

    @Autowired
    private IShipmentDAO shipmentDAO;

    public ShipmentServiceStub(){

    }

    public ShipmentServiceStub(IShipmentService shippingCostDAO){

    }

    @Override
    public Shipment findShipmentById(int id) {
        Shipment uniqueShipId = shipmentDAO.findShipmentById();
        return uniqueShipId;
    }

    @Override
    public Shipment saveEstimate(Shipment shipment) throws Exception {
        return shipmentDAO.saveEstimate();
    }

    @Override
    public List<Shipment> fetchAllShipments() {
        return shipmentDAO.fetchAllShipments();
    }
}
