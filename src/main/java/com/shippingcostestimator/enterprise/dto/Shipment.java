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

    private String packageName;

    //The carrier (UPS, USPS, etc) should significantly impact the rates on the API.
    private String carrier;

    //!!!This value should not be touched outside of stub testing! This should be handled by the API!
    private double rates;
    //!!!This value should not be touched outside of stub testing! This should be handled by the API!

    //This value should return to *sum* of all the rates.
    private double totalRates;

    //Notably, there are three parts that make up a Shipment object: ToAddress, FromAddress, and Parcel.
    //If we are also doing international shipments, then we will need a fourth part: CustomsInfo.

    //FROM address map
    private String nameFrom;
    private String streetOneFrom;
    private String streetTwoFrom;
    private String cityFrom;
    private String stateFrom;
    private String countryFrom;
    private String zipFrom;
    //private Map<String, Object> toAddress = new HashMap<String, Object>();


    //TO address map
    private String nameTo;
    private String streetOneTo;
    private String streetTwoTo;
    private String cityTo;
    private String stateTo;
    private String countryTo;
    private String zipTo;
    //private Map<String, Object> fromAddress = new HashMap<String, Object>();

    //PARCEL
    private PackageInfo packageInfo;

    //private Map<String, Object> parcel = new HashMap<String, Object>();

    //private Map<String, Object> shipmentItem = new HashMap<String, Object>();
}
