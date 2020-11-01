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

    //Not sure why this doesn't work. If @Autowired is enabled here, then it tells me that no beans for IShipmentDao is found.
    @Autowired
    private IShipmentDAO shipmentDAO;

    public ShipmentServiceStub(){

    }

    public ShipmentServiceStub(IShipmentDAO shipmentDAO){
        this.shipmentDAO = shipmentDAO;
    }

    /***
     * Returns a Shipment object with a specified integer ID.
     * @param id
     * @return A shipment estimate based on specified ShipmentId.
     */
    @Override
    public Shipment findShipmentId(int id) {
        Shipment uniqueShipId = shipmentDAO.findShipId(id);
        return uniqueShipId;
    }

    /***
     * Saves a Shipment estimate.
     * @param shipment
     * @return Saved shipment estimate.
     * @throws Exception
     */
    @Override
    public Shipment saveEstimate(Shipment shipment) throws Exception {
        return shipmentDAO.saveEstimate(shipment);
    }

    /***
     * Returns a list of all Shipment objects that are currently in use by the program.
     * @return a list of all shipments.
     */
    @Override
    public List<Shipment> fetchAllShipments() {
        return shipmentDAO.fetchAllShipments();
    }
}
