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
import java.util.List;
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

        try{
            var rates = shipmentModel.getRates();
            model.addAttribute("rates", rates);
            for(Rate rate : rates){
                ShipmentRate shipmentRate = new ShipmentRate();
                shipmentRate.setObject(fromAddress.getFromStreetOne());
                shipmentRate.setCarrier(rate.getCarrier());
                shipmentRate.setService(rate.getService());
                shipmentRate.setRate(rate.getRate());
                shipmentRatesService.saveRate(shipmentRate);
            }
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

    @GetMapping("/rate")
    public ResponseEntity findAllRates(){
        List<ShipmentRate> allRates = shipmentRatesService.findAllRates();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(allRates, headers, HttpStatus.OK);
    }

    @DeleteMapping("/rate/{id}")
    public ResponseEntity deleteRate(@PathVariable("id") int id){
        try{
            shipmentRatesService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
