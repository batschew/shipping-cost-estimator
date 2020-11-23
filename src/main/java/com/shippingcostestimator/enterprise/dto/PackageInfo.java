package com.shippingcostestimator.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

public @Data
class PackageInfo {
    //PARCEL
    @SerializedName("packageInfoId")
    private int packageInfoId;
    @SerializedName("length")
    private double length;
    @SerializedName("width")
    private double width;
    @SerializedName("height")
    private double height;
    @SerializedName("predefinedPackage")
    private String predefinedPackage;
    @SerializedName("weight")
    private double weight;
}


