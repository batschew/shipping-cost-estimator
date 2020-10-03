package com.shippingcostestimator.enterprise.dto;

import lombok.Data;

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
    int packageId;
    int carrierId;
    int predefinedPackId;
    String packageName;

    String startAddress;
    String endAddress;

    int totalCostId;
    int serviceLevel;
    int rates;
    //int estArrival;
}
