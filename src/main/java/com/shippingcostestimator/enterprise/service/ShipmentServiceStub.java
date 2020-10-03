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

    //@Autowired
    private IShipmentDAO shipmentDAO;

    public ShipmentServiceStub(){

    }

    public ShipmentServiceStub(IShipmentDAO shipmentDAO){
        this.shipmentDAO = shipmentDAO;
    }

    @Override
    public Shipment findShipId(int id) {
        Shipment uniqueShipId = shipmentDAO.findShipId(id);
        return uniqueShipId;
    }

    @Override
    public Shipment saveEstimate(Shipment shipment) throws Exception {
        return shipmentDAO.saveEstimate(shipment);
    }

    @Override
    public List<Shipment> fetchAllShipments() {
        return shipmentDAO.fetchAllShipments();
    }
}
