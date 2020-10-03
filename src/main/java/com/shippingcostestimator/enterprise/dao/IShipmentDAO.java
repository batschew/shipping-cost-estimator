package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.Shipment;

import java.util.List;

public interface IShipmentDAO {

    Shipment findShipmentById();

    Shipment saveEstimate();

    List<Shipment> fetchAllShipments();
}
