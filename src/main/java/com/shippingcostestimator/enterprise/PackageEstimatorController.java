package com.shippingcostestimator.enterprise;

import com.shippingcostestimator.enterprise.dto.Shipment;
import com.shippingcostestimator.enterprise.service.IShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller // Decides what renders when a user hits a URL or endpoint
public class PackageEstimatorController {

    @Autowired
    IShipmentService shipmentService;

    /**
     * Handle the root ("/") endpoint and return a start page.
     * @return
     */
    @RequestMapping("/")
    public String index() {
        System.out.println("Hello!");
        //Sample input of info.
//        Shipment shipment = new Shipment();
//        shipment.setPackageId(1);
//        shipment.setCarrierId(1);
//        shipment.setPredefinedPackId(1);
//        shipment.setPackageName("Stub Shipment");
//
//        shipment.setStartAddress("Test Address, 123");
//        shipment.setEndAddress("Test Address, 321");
//
//        shipment.setServiceLevel(1);
//        shipment.setRates(25.32);

        //Map<String, Object> toAddressMap = new HashMap<String, Object>();

        //One big old test shipment.
        Shipment shipment = new Shipment();
        shipment.setPackageName("Stub Package");
        shipment.setPackageId(1);
        shipment.setCarrier("FirstClassPackageInternationalService");

        //!!!This is a stub! Rates are to be determined by the API - this is simply built-in for testing!!!
        shipment.setRates(9.50);
        //!!!This is a stub! Rates are to be determined by the API - this is simply built-in for testing!!!

        Map<String, Object> fromAddressMap = new HashMap<String, Object>();
        fromAddressMap.put("name", "Dude Guy");
        fromAddressMap.put("street1", "123 Street Ave.");
        fromAddressMap.put("street2", "321 Street Dr.");
        fromAddressMap.put("city", "Cincinnati");
        fromAddressMap.put("state", "OH");
        fromAddressMap.put("country", "USA");
        fromAddressMap.put("zip", "12345");
        shipment.setFromAddress(fromAddressMap);

        Map<String, Object> toAddressMap = new HashMap<String, Object>();
        toAddressMap.put("name", "Dude Guy");
        toAddressMap.put("street1", "456 Road Str.");
        toAddressMap.put("street2", "654 Road Rd.");
        toAddressMap.put("city", "Columbus");
        toAddressMap.put("state", "OH");
        toAddressMap.put("country", "USA");
        toAddressMap.put("zip", "45678");
        shipment.setToAddress(toAddressMap);

        Map<String, Object> parcelMap = new HashMap<String, Object>();
        parcelMap.put("length", 20.2);
        parcelMap.put("width", 10.5);
        parcelMap.put("height", 9.9);
        parcelMap.put("predefined_package", null);
        parcelMap.put("weight", 10);
        shipment.setParcel(parcelMap);

        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", toAddressMap);
        shipmentMap.put("from_address", fromAddressMap);
        shipmentMap.put("parcel", parcelMap);
        shipment.setShipmentItem(shipmentMap);
        System.out.println("Hello!");
        return "start";
    }

    @RequestMapping("/saveEstimate")
    public String saveEstimate(Shipment shipment){
        //Not sure why this needs a try/catch block...
        try {
            shipmentService.saveEstimate(shipment);
        } catch (Exception e) {
            e.printStackTrace();
            return "start";
        }
        return "start";
    }

    @GetMapping("/shipment")
    @ResponseBody
    public List<Shipment> fetchAllShipments(){

        return shipmentService.fetchAllShipments();

    }


}
