package com.shippingcostestimator.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/*
 * This is what connects the FromAddress, ToAddress, and PackageInfo maps together. Hopefully, this should solve the issue of the code not producing nested JSON.
 */
public @Data
class ShipmentMap {
    @SerializedName("id")
    private int id;
    @SerializedName("object")
    private String object;
    @SerializedName("to_address")
    private ToAddress toAddress;
    @SerializedName("from_address")
    private FromAddress fromAddress;
    @SerializedName("parcel")
    private PackageInfo parcel;
}