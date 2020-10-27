package com.shippingcostestimator.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

public @Data
class PackageInfo {
    //PARCEL
    private double length;
    private double width;
    private double height;
    @SerializedName("predefinedPackage")
    private String predefinedPackage;
    private double weight;
}

