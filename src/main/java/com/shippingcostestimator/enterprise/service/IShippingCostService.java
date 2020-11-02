package com.shippingcostestimator.enterprise.service;

import java.io.IOException;

public interface IShippingCostService {
    String fetchServiceLevel(String apiKey, int shipmentId) throws IOException;
    String fetchRates(String apiKey, int shipmentId) throws IOException;
    String fetchEstArrival(String apiKey) throws IOException;
}
