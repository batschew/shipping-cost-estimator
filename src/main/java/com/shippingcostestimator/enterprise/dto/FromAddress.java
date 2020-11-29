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
    @SerializedName("fromName")
    private String fromName;
    @SerializedName("fromCompany")
    private String fromCompany;
    @SerializedName("fromStreetOne")
    private String fromStreetOne;
    @SerializedName("fromStreetTwo")
    private String fromStreetTwo;
    @SerializedName("fromCity")
    private String fromCity;
    @SerializedName("fromState")
    private String fromState;
    @SerializedName("fromZip")
    private String fromZip;
    @SerializedName("fromCountry")
    private String fromCountry;
    @SerializedName("fromPhone")
    private String fromPhone;
    @SerializedName("fromMode")
    private String fromMode;
    @SerializedName("carrier_facility")
    private String fromCarrierFacility;
    @SerializedName("residential")
    private String fromResidential;
    @SerializedName("email")
    private String fromEmail;
}