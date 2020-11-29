package com.shippingcostestimator.enterprise;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Rate;
import com.easypost.model.Shipment;
import com.shippingcostestimator.enterprise.dto.*;
import com.shippingcostestimator.enterprise.service.IShipmentMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller // Decides what renders when a user hits a URL or endpoint
public class PackageEstimatorController {

    @Autowired
    IShipmentMapService ShipmentMapService;

    Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * Handle the root ("/") endpoint and return a start page.
     * @return start.html page.
     */
    @RequestMapping("/")
    public String index(Model model){
        EasyPost.apiKey = "EZTK773d7864beb64671aadc6a9d64777a6boAq0h9WzLFA70as0wPnkrQ";
        ShipmentMap shipment = new ShipmentMap();
        FromAddress fromAddress = new FromAddress();
        ToAddress toAddress = new ToAddress();
        PackageInfo parcel = new PackageInfo();

        model.addAttribute(fromAddress);
        model.addAttribute(toAddress);
        model.addAttribute("packageInfo", parcel);
        return "start";
    }

    @PostMapping("/saveShipmentMap")
    public String saveShipmentMap(FromAddress fromAddress, ToAddress toAddress, PackageInfo packageInfo){

        EasyPost.apiKey = "EZTK773d7864beb64671aadc6a9d64777a6boAq0h9WzLFA70as0wPnkrQ";

        //Declare variables
        ShipmentMap shipment = new ShipmentMap();
        HashMap toAddressMap = new HashMap<String, Object>();
        HashMap fromAddressMap = new HashMap<String, Object>();
        HashMap parcelMap = new HashMap<String, Object>();

        //set object maps from UI model
        fromAddressMap.put("street1", fromAddress.getFromStreetOne());
        fromAddressMap.put("street2", fromAddress.getFromStreetTwo());
        fromAddressMap.put("city", fromAddress.getFromCity());
        fromAddressMap.put("zip", fromAddress.getFromZip());
        fromAddressMap.put("state", fromAddress.getFromState());

        toAddressMap.put("street1", toAddress.getToStreetOne());
        toAddressMap.put("street2", toAddress.getToStreetTwo());
        toAddressMap.put("city", toAddress.getToCity());
        toAddressMap.put("zip", toAddress.getToZip());
        toAddressMap.put("state", toAddress.getToState());

        parcelMap.put("weight", packageInfo.getWeight());

        shipment.setFromAddress(fromAddressMap);
        shipment.setToAddress(toAddressMap);
        shipment.setParcel(parcelMap);

        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", shipment.getToAddress());
        shipmentMap.put("from_address", shipment.getFromAddress());
        shipmentMap.put("parcel", shipment.getParcel());

        //EASYPOST logic
        com.easypost.model.Shipment shipmentModel = null;
        //Create easypost shipment
        try {
            shipmentModel = com.easypost.model.Shipment.create(shipmentMap);
        } catch (EasyPostException e) {
            e.printStackTrace();
        }

        //Console logging for debugging purposes, remove for production
        var rates = shipmentModel.getRates();
        for(Rate rate : rates){
            System.out.println("Carrier: " + rate.getCarrier());
            System.out.println("Service level: " + rate.getService());
            System.out.println("Est Delivery Days: " + rate.getEstDeliveryDays());
            System.out.println("Delivery Days: " + rate.getDeliveryDays());
            System.out.println("Rate: " + rate.getRate());
            System.out.println("");
        }

        try{
            ShipmentMapService.saveEstimate(shipment);
        }catch(Exception e){
            e.printStackTrace();
            return "start";
        }
        return "start";
    }

    @PostMapping(value="/shipmentMap", consumes="application/json", produces="application/json")
    public ResponseEntity createShipmentMap(@RequestBody ShipmentMap shipmentMap){
        ShipmentMap newShipment;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try{
            newShipment = ShipmentMapService.saveEstimate(shipmentMap);
        }catch(Exception e){
            return new ResponseEntity(headers, HttpStatus.CONFLICT);
        }
        return new ResponseEntity(newShipment, headers, HttpStatus.CREATED);
    }



    /*
     * Create a new shipment object with the data inputted in the UI.
     *
     * Returns:
     * 201: Successful creation
     * 409: Can't create because it already exists
     *
     * @param shipment a JSON representation of the shipment object
     * @return the newly created shipment object
     */
//    @PostMapping(value="/shipment", consumes="application/json", produces="application/json")
//    public ResponseEntity createShipment(@RequestBody Shipment shipment){
//        Shipment newShipment;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        try{
//            newShipment = shipmentService.saveEstimate(shipment);
//        }catch(Exception e){
//            return new ResponseEntity(headers, HttpStatus.CONFLICT);
//        }
//        return new ResponseEntity(newShipment, headers, HttpStatus.CREATED);
//    }


}
