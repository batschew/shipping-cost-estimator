package com.shippingcostestimator.enterprise.service.PackageInfo;

import java.io.IOException;

public interface IPackageInfoService {
    String fetchPredefinedPackage(String apiKey, int shipmentId) throws IOException;
}
