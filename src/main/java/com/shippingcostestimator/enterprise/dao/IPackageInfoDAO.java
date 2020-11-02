package com.shippingcostestimator.enterprise.dao;

import java.io.IOException;

public interface IPackageInfoDAO {
    String fetchPredefinedPackage(String apiKey, int shipmentId) throws IOException;
}
