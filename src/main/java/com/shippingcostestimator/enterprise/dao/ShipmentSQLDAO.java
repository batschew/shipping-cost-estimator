package com.shippingcostestimator.enterprise.dao;

import com.shippingcostestimator.enterprise.dto.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("shipmentDAO")
@Profile("dev")
public class ShipmentSQLDAO implements IShipmentDAO{

    @Autowired
    ShipmentRepository shipmentRepository;

    @Override
    public Shipment findShipId(int id) {
        return shipmentRepository.findById(id).get();
    }

    @Override
    public Shipment saveEstimate(Shipment shipment) {
        Shipment createdShipment = shipmentRepository.save(shipment);
        return createdShipment;
    }

    /* In this case, .findAll(); doesn't return a List object, but instead an Iterable.
     To remedy this, we have two options.
      One, we can change all the methods involving fetchAllShipments to take an Iterable instead of a List.
      Or two, we do what I did below, where we converted it from an Iterable to a List.
      The professor suggests that for smaller projects, the second method is a good idea.
      He also said that for larger projects, it would be better to bite the bullet and do the first method. */
    @Override
    public List<Shipment> fetchAllShipments() {
        List<Shipment> allShipments = new ArrayList<>();
        Iterable<Shipment> shipments = shipmentRepository.findAll();
        for(Shipment shipment : shipments){
            allShipments.add(shipment);
        }
        return allShipments;
    }
}
