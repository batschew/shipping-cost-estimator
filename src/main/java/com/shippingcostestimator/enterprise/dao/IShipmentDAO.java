package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.Shipment;

import java.util.List;

public interface IShipmentDAO {

    Shipment findShipId(int id);

    Shipment saveEstimate(Shipment id);

    List<Shipment> fetchAllShipments();

}
