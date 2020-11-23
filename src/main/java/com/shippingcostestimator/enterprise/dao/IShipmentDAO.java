package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.Shipment;

import java.util.List;

public interface IShipmentDAO {

    /**
     * Finds a Shipment object by its corresponding ID.
     * @param id
     * @return Shipment
     */
    Shipment findShipId(int id);

    /**
     * Saves estimated cost of a shipment in a Shipment object.
     * @param id
     * @return Shipment
     */
    Shipment saveEstimate(Shipment id);

    /**
     * Fetches a list of all Shipment objects.
     * @return List<Shipment>
     */
    List<Shipment> fetchAllShipments();

}
