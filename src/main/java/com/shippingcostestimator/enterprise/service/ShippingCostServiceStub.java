package com.shippingcostestimator.enterprise.service;

import com.shippingcostestimator.enterprise.dao.IShippingCostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ShippingCostServiceStub implements IShippingCostService {

    @Autowired
    private IShippingCostDAO shippingCostDAO;


    @Override
    @Cacheable("serviceLevel")
    public String fetchServiceLevel(String apiKey, int shipmentId) throws IOException {
        return shippingCostDAO.fetchServiceLevel(apiKey, shipmentId);
    }

    @Cacheable("rates")
    @Override
    public String fetchRates(String apiKey, int shipmentId) throws IOException {
        return shippingCostDAO.fetchRates(apiKey, shipmentId);
    }

    @Cacheable("estArrival")
    @Override
    public String fetchEstArrival(String apiKey) throws IOException {
        return shippingCostDAO.fetchEstArrival(apiKey);
    }
}
