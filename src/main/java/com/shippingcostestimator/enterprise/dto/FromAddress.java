package com.shippingcostestimator.enterprise.dto;

import com.google.gson.annotations.SerializedName;

/*
* FromAddress map. This is what should be the "start" address of a shipment.
 */
public class FromAddress {
    @SerializedName("id")
    private String id;
    @SerializedName("object")
    private String object;
    @SerializedName("name")
    private String name;
    @SerializedName("company")
    private String company;
    @SerializedName("street1")
    private String street1;
    @SerializedName("street2")
    private String street2;
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
