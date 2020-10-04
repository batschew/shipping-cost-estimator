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
