package com.shippingcostestimator.enterprise.dao;

import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.List;

public interface IShippingCostDAO {
    /**
     * Fetches the Service Level for a Shipment object using its ID. Requires EasyPost API Key.
     * @param apiKey
     * @param shipmentId
     * @return String
     * @throws IOException
     */
    String fetchServiceLevel(String apiKey, int shipmentId) throws IOException;

    /**
     * Fetches the Rate for a Shipment object using its ID. Requires EasyPost API Key.
     * @param apiKey
     * @param shipmentId
     * @return String
     * @throws IOException
     */
    String fetchRates(String apiKey, int shipmentId) throws IOException;

    /**
     * Fetches the Estimated Arrival date for a Shipment object using its ID. Requires EasyPost API Key.
     * @param apiKey
     * @return String
     * @throws IOException
     */
    String fetchEstArrival(String apiKey) throws IOException;
}
