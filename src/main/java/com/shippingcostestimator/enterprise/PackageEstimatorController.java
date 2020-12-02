package com.shippingcostestimator.enterprise;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Rate;
import com.shippingcostestimator.enterprise.dto.*;
import com.shippingcostestimator.enterprise.service.IShipmentRatesService;
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
    IShipmentRatesService ShipmentRatesService;

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

    @RequestMapping("/shipments")
    public String shipments(Model model){
        var shipments = ShipmentRatesService.findAllRates();
        for(ShipmentRate shipment : shipments){
            System.out.println("Carrier: " + shipment.getCarrier());
            System.out.println("Service level: " + shipment.getService());
            System.out.println("Rate: $" + shipment.getRate());
            System.out.println("Object: " + shipment.getObject());
        }
        model.addAttribute("shipments", shipments);
        return "shipments";
    }
    /*
    * Saves a new ShipmentRates object.
    *
    * saveShipmentMap does two things: it creates a Shipment object that is consumed by the API,
    * and then it produces three Rates objects and saves it to a database.
    *
    * @param fromAddress the fromAddress map
    * @param toAddress the toAddress map
    * @param packageInfo the packageInfo map
    * @return start
     */
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

        parcelMap.put("length", packageInfo.getLength());
        parcelMap.put("height", packageInfo.getHeight());
        parcelMap.put("width", packageInfo.getWidth());
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

        //Parse Shipment object's rates and save each one to an object.
        try{
            var rates = shipmentModel.getRates();
            model.addAttribute("rates", rates);

            //For each rate, save to an object.
            for(Rate rate : rates){
                ShipmentRate shipmentRate = new ShipmentRate();
                shipmentRate.setObject(fromAddress.getFromStreetOne());
                shipmentRate.setCarrier(rate.getCarrier());
                shipmentRate.setService(rate.getService());
                shipmentRate.setRate(rate.getRate());

                //Save to database.
                ShipmentRatesService.saveRate(shipmentRate);
            }
        }catch(Exception e){
            e.printStackTrace();
            return "start";
        }
        return "start";

    }

//    /*
//    * Creates a new Shipment object.
//    *
//    * returns one of two status codes:
//    * 201: Created
//    * 409: Conflict
//     */
//    @PostMapping(value="/shipmentMap", consumes="application/json", produces="application/json")
//    public ResponseEntity createShipmentMap(@RequestBody ShipmentMap shipmentMap){
//        ShipmentMap newShipment;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        try{
//            newShipment = ShipmentMapService.saveEstimate(shipmentMap);
//        }catch(Exception e){
//            return new ResponseEntity(headers, HttpStatus.CONFLICT);
//        }
//        return new ResponseEntity(newShipment, headers, HttpStatus.CREATED);
//    }

    /*
    * Finds a specific shipmentRate object by its ID.
    *
    * returns a shipmentRate and a 200 OK status code.
     */
    @GetMapping("/rate/{id}")
    public ResponseEntity findShipmentRate(@PathVariable("id") int id){
        ShipmentRate foundRate = ShipmentRatesService.findRate(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundRate, headers, HttpStatus.OK);
    }

    /*
    * Fetches all the shipmentRate objects.
    *
    * returns all shipmentRates.
     */
    @GetMapping("/rate")
    public ResponseEntity findAllRates(){
        List<ShipmentRate> allRates = ShipmentRatesService.findAllRates();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(allRates, headers, HttpStatus.OK);
    }

    /*
    * Deletes a specific shipmentRate by id.
     */
    @DeleteMapping("/rate/{id}")
    public ResponseEntity deleteRate(@PathVariable("id") int id){
        try{
            ShipmentRatesService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
