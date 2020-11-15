package com.shippingcostestimator.enterprise;

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

import java.util.List;

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
    public String index(Model model) {
//        //One big old test shipment.
//        Shipment shipment = new Shipment();
//        shipment.setPackageName("Stub Package");
//        shipment.setPackageId(1);
//        shipment.setCarrier("FirstClassPackageInternationalService");
//        shipment.setStreetOneFrom("2600 Clifton Ave");
//        shipment.setCityFrom("Cincinnati");
//        shipment.setCountryFrom("United States");
//        //!!!This is a stub! Rates are to be determined by the API - this is simply built-in for testing!!!
//        shipment.setRates(9.50);
//        //!!!This is a stub! Rates are to be determined by the API - this is simply built-in for testing!!!

        ShipmentMap shipment = new ShipmentMap();
        FromAddress fromAddress = new FromAddress();
        ToAddress toAddress = new ToAddress();
        PackageInfo parcel = new PackageInfo();


        fromAddress.setId(1);
        fromAddress.setStreet1("1234 Street");

        toAddress.setId(1);
        toAddress.setStreet1("3421 Avenue");

        parcel.setPackageInfoId(1);
        parcel.setWeight(32.50);

        shipment.setId(1);
        shipment.setFromAddress(fromAddress);
        shipment.setToAddress(toAddress);
        shipment.setParcel(parcel);

        model.addAttribute(shipment);
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
