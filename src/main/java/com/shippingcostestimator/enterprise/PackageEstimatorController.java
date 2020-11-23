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
        EasyPost.apiKey = "x";
        ShipmentMap shipment = new ShipmentMap();
        FromAddress fromAddress = new FromAddress();
        ToAddress toAddress = new ToAddress();
        PackageInfo parcel = new PackageInfo();


        fromAddress.setId(1);
        fromAddress.setStreetOne("1234 Street");
        fromAddress.setZip("1");
        String streetOneValue = fromAddress.getStreetOne();
        String originZip = fromAddress.getZip();

        toAddress.setId(1);
        toAddress.setStreetOne("3421 Avenue");
        toAddress.setZip("1");
        String streetTwoValue = toAddress.getStreetOne();
        String destinationZip = toAddress.getZip();

        parcel.setPackageInfoId(1);
        //parcel.setPredefinedPackage("MediumFlatRateBox");
        parcel.setWeight(32.50);
        double weight = parcel.getWeight();
        //String predefinedPackage = parcel.getPredefinedPackage();

        //shipment.setId(1);

        HashMap toAddressMap = new HashMap<String, Object>();
        toAddressMap.put("street1", streetTwoValue);
        toAddressMap.put("zip", destinationZip);

        HashMap fromAddressMap = new HashMap<String, Object>();
        fromAddressMap.put("street1", streetOneValue);
        fromAddressMap.put("zip", originZip);

        HashMap parcelMap = new HashMap<String, Object>();
        parcelMap.put("weight", weight);
        //parcelMap.put("predefined_package", predefinedPackage);

        shipment.setFromAddress(fromAddressMap);
        shipment.setToAddress(toAddressMap);
        shipment.setParcel(parcelMap);

        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", shipment.getToAddress());
        shipmentMap.put("from_address", shipment.getFromAddress());
        shipmentMap.put("parcel", shipment.getParcel());



        //After this point, we make the shipment itself and send it through the API.
        com.easypost.model.Shipment shipmentTwo = null;
        Rate rate = null;
        try {
            shipmentTwo = com.easypost.model.Shipment.create(shipmentMap);
            rate = shipmentTwo.lowestRate();
        } catch (EasyPostException e) {
            e.printStackTrace();
        }



        model.addAttribute(rate);
        return "start";
    }

    @PostMapping("/saveShipmentMap")
    public String saveShipmentMap(ShipmentMap shipment){
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
