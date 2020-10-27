package com.shippingcostestimator.enterprise.service;

import com.shippingcostestimator.enterprise.dao.IPackageInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PackageInfoServiceStub implements IPackageInfoService {

    @Autowired
    private IPackageInfoDAO packageInfoDAO;

    @Override
    public String fetchPredefinedPackage(String apiKey, int shipmentId) throws IOException {
        return packageInfoDAO.fetchPredefinedPackage(apiKey, shipmentId);
    }
}
