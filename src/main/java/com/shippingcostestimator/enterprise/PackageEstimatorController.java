package com.shippingcostestimator.enterprise;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.shippingcostestimator.enterprise.dto.*;
import com.shippingcostestimator.enterprise.service.IShipmentService;
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
    IShipmentService shipmentService;

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
        fromAddress.setStreet1("1234 Street");
        fromAddress.setZip("");
        String streetOneValue = fromAddress.getStreet1();
        String originZip = fromAddress.getZip();

        toAddress.setId(1);
        toAddress.setStreet1("3421 Avenue");
        toAddress.setZip("");
        String streetTwoValue = toAddress.getStreet1();
        String destinationZip = toAddress.getZip();

        parcel.setPackageInfoId(1);
        parcel.setWeight(32.50);
        double weight = parcel.getWeight();

        shipment.setId(1);

        Map<String, Object> toAddressMap = new HashMap<String, Object>();
        toAddressMap.put("street1", streetTwoValue);
        toAddressMap.put("zip", destinationZip);

        Map<String, Object> fromAddressMap = new HashMap<String, Object>();
        fromAddressMap.put("street1", streetOneValue);
        fromAddressMap.put("zip", originZip);

        Map<String, Object> parcelMap = new HashMap<String, Object>();
        parcelMap.put("weight", weight);

        shipment.setFromAddress(fromAddress);
        shipment.setToAddress(toAddress);
        shipment.setParcel(parcel);

        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", toAddressMap);
        shipmentMap.put("from_address", fromAddressMap);
        shipmentMap.put("parcel", parcelMap);


        com.easypost.model.Shipment shipmentTwo = null;
        try {
            shipmentTwo = com.easypost.model.Shipment.create(shipmentMap);
        } catch (EasyPostException e) {
            e.printStackTrace();
        }

        model.addAttribute(shipmentTwo);
        return "start";
    }

    /***
     * Handle the saveEstimate ("/saveEstimate") endpoint and save a Shipment object using data specified by the user.
     * @param shipment
     * @return start.html page.
     */
    @RequestMapping("/saveEstimate")
    public String saveEstimate(Shipment shipment){
        //Not sure why this needs a try/catch block...
        log.debug("Entering saveEstimate endpoint");
        try {
            shipmentService.saveEstimate(shipment);
            log.info("Saving estimate");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error saving estimate. Message: " + e.getMessage());
            return "start";
        }
        return "start";
    }

    /***
     * Handle the shipment ("/shipment") endpoint and return all Shipment objects that have been created from data specified by the user.
     * @return all shipments in the list.
     */
    @GetMapping("/shipment")
    @ResponseBody
    public List<Shipment> fetchAllShipments(){
        return shipmentService.fetchAllShipments();
    }

    /*
     * Find a shipment with a given ID.
     * Given the parameter ID, find the shipment that has the same ID.
     *
     * Returns status code:
     * 200: shipment found
     * 400: shipment not found
     *
     * @param id the unique identifier of a shipment
     */
    @GetMapping("/shipment/{id}/")
    public ResponseEntity fetchShipmentById(@PathVariable("id") int id){
        log.debug("Entering fetchShipmentById endpoint");
        Shipment foundShipment;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try{
            log.info("Found shipment");
            foundShipment = shipmentService.findShipmentId(id);
            return new ResponseEntity(foundShipment, headers, HttpStatus.OK);
        }catch(Exception e){
            log.info("Did not find shipment with a 400 BAD_REQUEST error. Message: " + e.getMessage());
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/saveShipment")
//    public String saveShipment(Shipment shipment){
//        try{
//            shipmentService.saveEstimate(shipment);
//        }catch(Exception e){
//
//        }
//    }

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
