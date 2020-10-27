package com.shippingcostestimator.enterprise.dao;

import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.List;

public interface IShippingCostDAO {
    String fetchServiceLevel(String apiKey, int shipmentId) throws IOException;
    String fetchRates(String apiKey, int shipmentId) throws IOException;
    String fetchEstArrival(String apiKey) throws IOException;
}
