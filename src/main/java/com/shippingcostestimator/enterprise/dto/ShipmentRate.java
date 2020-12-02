package com.shippingcostestimator.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class ShipmentRate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @SerializedName("id")
    private int id;
    @SerializedName("fromRateAddress")
    private String fromRateAddress;
    @SerializedName("toRateAddress")
    private String toRateAddress;
    @SerializedName("service")
    private String service;
    @SerializedName("rate")
    private Float rate;
    @SerializedName("carrier")
    private String carrier;
//    @SerializedName("delivery_days")
//    private Number deliveryDays;
//    @SerializedName("delivery_date")
//    private String deliveryDate;
}
