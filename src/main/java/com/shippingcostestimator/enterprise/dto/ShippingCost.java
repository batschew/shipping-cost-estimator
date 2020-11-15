package com.shippingcostestimator.enterprise.dto;

import com.google.gson.annotations.SerializedName;
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
*
* Not in use in this branch! Not being removed quite yet, as I'm shifting the code to rely more on the new DTOs so the program will work better with the API.
 */
public @Data
class ShippingCost {
    private int totalCostId;
    @SerializedName("serviceLevel")
    private int serviceLevel;
    @SerializedName("rates")
    private int rates;
    @SerializedName("estArrival")
    private int estArrival;
}
