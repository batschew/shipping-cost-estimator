package com.shippingcostestimator.enterprise.dto;

import lombok.Data;

/*
* DTO for the addresses describing the start and end location of a shipment - uses Lombok.
* !!!Has no use, currently. Variables included in Shipment.!!!
*
* startAddress defines the start location.
* endAddress defines the end location.
* We may just be able to merge this with another DTO, since there's so little data here.
 */
public @Data
class Destination {
    private String startAddress;
    private String endAddress;
}
