package com.shippingcostestimator.enterprise;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Rate;
import com.shippingcostestimator.enterprise.dto.*;
import com.shippingcostestimator.enterprise.service.IShipmentMapService;
import com.shippingcostestimator.enterprise.service.ShipmentRates.IShipmentRatesService;
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
import java.util.Map;

@Controller // Decides what renders when a user hits a URL or endpoint
public class PackageEstimatorController {

    @Autowired
    IShipmentMapService shipmentMapService;

    @Autowired
    IShipmentRatesService shipmentRatesService;

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
    public String saveShipmentMap(FromAddress fromAddress, ToAddress toAddress, PackageInfo packageInfo, Model model){

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
        model.addAttribute("rates", rates);
        ShipmentRate shipmentRate = new ShipmentRate();
        for(Rate rate : rates){
            String service = "Priority";
//            if(service.equals(rate.getService())){
//                //System.out.println("YES");
//
//            }

            shipmentRate.setCarrier(rate.getCarrier());
            shipmentRate.setService(rate.getService());
            shipmentRate.setRate(rate.getRate());
            System.out.println("Carrier: " + shipmentRate.getCarrier());
            System.out.println("Service level: " + shipmentRate.getService());
            System.out.println("Est Delivery Days: " + rate.getEstDeliveryDays());
            System.out.println("Rate: $" + shipmentRate.getRate());
            System.out.println("");
            shipmentRatesService.saveRate(shipmentRate);
        }

        try{
            shipmentMapService.saveEstimate(shipment);
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
            newShipment = shipmentMapService.saveEstimate(shipmentMap);
        }catch(Exception e){
            return new ResponseEntity(headers, HttpStatus.CONFLICT);
        }
        return new ResponseEntity(newShipment, headers, HttpStatus.CREATED);
    }

    @GetMapping("/rate/{id}")
    public ResponseEntity findShipmentRate(@PathVariable("id") int id){
        ShipmentRate foundRate = shipmentRatesService.findRate(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundRate, headers, HttpStatus.OK);
    }
}
