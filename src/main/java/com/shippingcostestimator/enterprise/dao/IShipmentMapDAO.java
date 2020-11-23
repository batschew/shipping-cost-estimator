package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.ShipmentMap;

import java.util.List;

public interface IShipmentMapDAO {

    ShipmentMap findShipById(int id);

    ShipmentMap saveEstimate(ShipmentMap id);

    List<ShipmentMap> fetchAllShipments();

}
