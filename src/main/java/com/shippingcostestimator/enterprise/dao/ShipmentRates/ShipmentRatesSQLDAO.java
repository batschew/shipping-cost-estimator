package com.shippingcostestimator.enterprise.dao.ShipmentRates;

import com.shippingcostestimator.enterprise.dto.ShipmentRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShipmentRatesSQLDAO implements IShipmentRatesDAO{

    //Map<Integer, ShipmentRate> allShipmentRates = new HashMap<>();

    @Autowired
    ShipmentRatesRepository shipmentRatesRepository;

    @Override
    public ShipmentRate saveRate(ShipmentRate shipmentRate) {
        ShipmentRate savedShipment = shipmentRatesRepository.save(shipmentRate);
        return savedShipment;
    }

    @Override
    public ShipmentRate findRateById(int id) {
        return shipmentRatesRepository.findById(id).get();
    }

    @Override
    public List<ShipmentRate> findAllRates() {
        List<ShipmentRate> shipmentList = new ArrayList<>();
        Iterable<ShipmentRate> iteratedList = shipmentRatesRepository.findAll();
        for(ShipmentRate shipmentRate : iteratedList){
            shipmentList.add(shipmentRate);
        }
        return shipmentList;
    }

    @Override
    public void delete(int id){
        shipmentRatesRepository.deleteById(id);
    }
}
