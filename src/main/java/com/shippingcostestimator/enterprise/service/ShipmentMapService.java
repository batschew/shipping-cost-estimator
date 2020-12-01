package com.shippingcostestimator.enterprise.service;

import com.shippingcostestimator.enterprise.dao.IShipmentMapDAO;
import com.shippingcostestimator.enterprise.dto.ShipmentMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentMapService implements IShipmentMapService{

    @Autowired
    private IShipmentMapDAO shipmentMapDAO;

//    @Autowired
//    private IShipmentRatesDAO shipmentRatesDAO;

    public ShipmentMapService(IShipmentMapDAO shipmentMapDAO){
        this.shipmentMapDAO = shipmentMapDAO;
    }

    /***
     * Returns a Shipment object with a specified integer ID.
     * @param id
     * @return A shipment estimate based on specified ShipmentId.
     */
    @Override
    public ShipmentMap findShipmentById(int id) {
        ShipmentMap uniqueId = shipmentMapDAO.findShipById(id);
        return uniqueId;
    }

    /***
     * Saves a Shipment estimate.
     * @param shipmentMap
     * @return Saved shipment estimate.
     * @throws Exception
     */
    @Override
    public ShipmentMap saveEstimate(ShipmentMap shipmentMap) throws Exception {
        return shipmentMapDAO.saveEstimate(shipmentMap);
    }

    /***
     * Returns a list of all Shipment objects that are currently in use by the program.
     * @return a list of all shipments.
     */
    @Override
    public List<ShipmentMap> fetchAllShipments() {
        return shipmentMapDAO.fetchAllShipments();
    }

//    @Override
//    public List<Rate> fetchAllRates(String rate) throws IOException {
//        return ShipmentRatesDAO.fetchShipmentRates(rate);
//    }
}
