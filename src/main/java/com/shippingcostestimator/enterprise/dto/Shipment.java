package com.shippingcostestimator.enterprise.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/*
* DTO responsible for the initial package traits - uses Lombok to handle getters and setters.
*
* packageId is going to be the ID for that specific package,
* carrierId is to be the API ID for specific carriers,
* predefinedPackId is going to be the predefined ID for a package from a specific carrier.*
* predefinedPackId may need to be moved to another DTO, however.
 */
public @Data
class Shipment {
    private int packageId;
    //private int carrierId;
    private String carrier;
    //private int predefinedPackId;
    private String packageName;

    //The example in the API suggests that we use something like this to build a Shipment object.
    //Notably, there are three parts that make up a Shipment object: ToAddress, FromAddress, and Parcel.
    //If we are also doing international shipments, then we will need a fourth part: CustomsInfo.
    private Map<String, Object> toAddress = new HashMap<String, Object>();
    private Map<String, Object> fromAddress = new HashMap<String, Object>();
    private Map<String, Object> parcel = new HashMap<String, Object>();
    private Map<String, Object> shipmentItem = new HashMap<String, Object>();

    //TO address map
    //private String name;
    //private String street1;
    //private String street2;
    //private String city;
    //private String state;
    //private String country;
    //private String zip;

    //private int totalCostId;
    //private int serviceLevel;
    private double rates;
    private double totalRates;
    //int estArrival;
}
