package com.shippingcostestimator.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/*
 * FromAddress map. This is what should be the "start" address of a shipment.
 */
public @Data
class FromAddress {
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