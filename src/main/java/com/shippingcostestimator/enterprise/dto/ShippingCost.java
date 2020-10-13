package com.shippingcostestimator.enterprise.dto;

import lombok.Data;

/*
* DTO for determining the shipping cost - uses Lombok.
* !!!Not currently in use! Variables included in Shipment.!!!
*
* totalCostId is the ID for the cost, and does NOT determine the actual cost. May not need this.
* serviceLevel determines the API ID for the carrier service level. Should work with Shipment.carrierId.
* rates is the actual determined cost of the shipment from the API.
* estArrival is the estimated arrival time of the shipment. Unsure if this is inputted or calculated by the API.
* We may want to remove estArrival, depending on that.
 */
public @Data
class ShippingCost {
    private int totalCostId;
    private int serviceLevel;
    private int rates;
    private int estArrival;
}
