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
    @SerializedName("toName")
    private String toName;
    @SerializedName("toCompany")
    private String toCompany;
    @SerializedName("streetOne")
    private String toStreetOne;
    @SerializedName("streetTwo")
    private String toStreetTwo;
    @SerializedName("city")
    private String toCity;
    @SerializedName("state")
    private String toState;
    @SerializedName("zip")
    private String toZip;
    @SerializedName("country")
    private String toCountry;
    @SerializedName("phone")
    private String toPhone;
    @SerializedName("mode")
    private String toMode;
    @SerializedName("carrier_facility")
    private String carrierFacility;
    @SerializedName("residential")
    private String toResidential;
    @SerializedName("email")
    private String toEmail;
}