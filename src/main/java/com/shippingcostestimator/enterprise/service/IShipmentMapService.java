package com.shippingcostestimator.enterprise.service;

import com.easypost.model.Rate;
import com.shippingcostestimator.enterprise.dto.ShipmentMap;

import java.io.IOException;
import java.util.List;

/*
* Interface for the ShippingCostService service, and interacts with the ShippingCost DTO.
 */
public interface IShipmentMapService {
    ShipmentMap findShipmentById(int id);
    ShipmentMap saveEstimate(ShipmentMap shipmentMap) throws Exception;
    List<ShipmentMap> fetchAllShipments();
//    List<Rate> fetchAllRates(String rates) throws IOException;

}
