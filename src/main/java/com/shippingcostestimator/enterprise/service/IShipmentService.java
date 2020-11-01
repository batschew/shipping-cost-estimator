package com.shippingcostestimator.enterprise.service;

import com.shippingcostestimator.enterprise.dto.Shipment;

import java.util.List;

/*
* Interface for the ShippingCostService service, and interacts with the ShippingCost DTO.
 */
public interface IShipmentService {
    Shipment findShipmentId(int id);
    Shipment saveEstimate(Shipment shipment) throws Exception;
    List<Shipment> fetchAllShipments();


}
