package com.shippingcostestimator.enterprise.dao.PackageInfo;

import java.io.IOException;

public interface IPackageInfoDAO {
    String fetchPredefinedPackage(String apiKey, int shipmentId) throws IOException;
}
