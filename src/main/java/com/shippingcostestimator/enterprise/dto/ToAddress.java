package com.shippingcostestimator.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/*
 * ToAddress map. This is what the "destination" should be on a shipment.
 */
public @Data
class ToAddress {
    @SerializedName("id")
    private int id;
    @SerializedName("object")
    private String object;
    @SerializedName("name")
    private String name;
    @SerializedName("company")
    private String company;
    @SerializedName("streetOne")
    private String streetOne;
    @SerializedName("streetTwo")
    private String streetTwo;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("zip")
    private String zip;
    @SerializedName("country")
    private String country;
    @SerializedName("phone")
    private String phone;
    @SerializedName("mode")
    private String mode;
    @SerializedName("carrier_facility")
    private String carrierFacility;
    @SerializedName("residential")
    private String residential;
    @SerializedName("email")
    private String email;
}