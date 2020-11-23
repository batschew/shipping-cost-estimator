package com.shippingcostestimator.enterprise.dao;

import java.io.IOException;

public interface IPackageInfoDAO {
    /**
     * Fetches the Predefined Package that has been selected for a Shipment object using its ID. Requires EasyPost API Key.
     * @param apiKey
     * @param shipmentId
     * @return
     * @throws IOException
     */
    String fetchPredefinedPackage(String apiKey, int shipmentId) throws IOException;
}
