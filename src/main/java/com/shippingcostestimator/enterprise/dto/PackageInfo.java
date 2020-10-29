package com.shippingcostestimator.enterprise.dto;

import lombok.Data;

public @Data
class PackageInfo {
    //PARCEL
    private int packageInfoId;
    private double length;
    private double width;
    private double height;
    private String predefinedPackage;
    private double weight;
}

