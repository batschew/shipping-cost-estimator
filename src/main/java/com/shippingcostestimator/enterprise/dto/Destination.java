package com.shippingcostestimator.enterprise.dto;

import lombok.Data;

/*
* DTO for the addresses describing the start and end location of a shipment - uses Lombok.
*
* startAddress defines the start location.
* endAddress defines the end location.
* We may just be able to merge this with another DTO, since there's so little data here.
 */
public @Data
class Destination {
    String startAddress;
    String endAddress;
}
