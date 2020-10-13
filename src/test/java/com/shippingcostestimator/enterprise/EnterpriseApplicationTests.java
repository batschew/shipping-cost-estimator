package com.shippingcostestimator.enterprise;

import com.shippingcostestimator.enterprise.dao.IShipmentDAO;
import com.shippingcostestimator.enterprise.dto.PackageInfo;
import com.shippingcostestimator.enterprise.dto.Shipment;
import com.shippingcostestimator.enterprise.dto.ShippingCost;
import com.shippingcostestimator.enterprise.service.IShipmentService;
import com.shippingcostestimator.enterprise.service.ShipmentServiceStub;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class EnterpriseApplicationTests {

    private IShipmentService shipmentService;
    private Shipment shipment = new Shipment();
    private PackageInfo packageInfo = new PackageInfo();

    @MockBean
    private IShipmentDAO shipmentDAO;

    @Test
    void contextLoads() {
    }

    //Checks if fetching a shipment functions correctly.
    @Test
    void fetchShipmentById_returnsShipmentWithId1() throws Exception{
        givenShipmentDataAvailable();
        whenShipmentWithId1Exists();
        whenShipmentDataWithId1();
        thenReturnShipmentWithId1();
    }

    private void givenShipmentDataAvailable(){
        Mockito.when(shipmentDAO.saveEstimate(shipment)).thenReturn(shipment);
        shipmentService = new ShipmentServiceStub(shipmentDAO);
    }

    private void whenShipmentWithId1Exists(){

        shipment.setPackageName("Stub Package");
        shipment.setPackageId(1);
        shipment.setCarrier("FirstClassPackageInternationalService");

        //!!!This is a stub! Rates are to be determined by the API - this is simply built-in for testing!!!
        shipment.setRates(9.50);
        //!!!This is a stub! Rates are to be determined by the API - this is simply built-in for testing!!!

//        Map<String, Object> fromAddressMap = new HashMap<String, Object>();
//        fromAddressMap.put("name", "Dude Guy");
//        fromAddressMap.put("street1", "123 Street Ave.");
//        fromAddressMap.put("street2", "321 Street Dr.");
//        fromAddressMap.put("city", "Cincinnati");
//        fromAddressMap.put("state", "OH");
//        fromAddressMap.put("country", "USA");
//        fromAddressMap.put("zip", "12345");
//        shipment.setFromAddress(fromAddressMap);
//
//        Map<String, Object> toAddressMap = new HashMap<String, Object>();
//        toAddressMap.put("name", "Dude Guy");
//        toAddressMap.put("street1", "456 Road Str.");
//        toAddressMap.put("street2", "654 Road Rd.");
//        toAddressMap.put("city", "Columbus");
//        toAddressMap.put("state", "OH");
//        toAddressMap.put("country", "USA");
//        toAddressMap.put("zip", "45678");
//        shipment.setToAddress(toAddressMap);
//
//        Map<String, Object> parcelMap = new HashMap<String, Object>();
//        parcelMap.put("length", 20.2);
//        parcelMap.put("width", 10.5);
//        parcelMap.put("height", 9.9);
//        parcelMap.put("predefined_package", null);
//        parcelMap.put("weight", 10);
//        shipment.setParcel(parcelMap);
//
//        Map<String, Object> shipmentMap = new HashMap<String, Object>();
//        shipmentMap.put("to_address", toAddressMap);
//        shipmentMap.put("from_address", fromAddressMap);
//        shipmentMap.put("parcel", parcelMap);
//        shipment.setShipmentItem(shipmentMap);

        shipment.setNameFrom("Dude McGuy");
        shipment.setStreetOneFrom("123 Street Ave.");
        shipment.setCityFrom("Cincinnati");
        shipment.setStateFrom("OH");
        shipment.setCountryFrom("USA");
        shipment.setZipFrom("12345");

        shipment.setNameTo("Stu McGee");
        shipment.setStreetOneTo("321 Road Dr.");
        shipment.setCityTo("Columbus");
        shipment.setStateTo("OH");
        shipment.setCountryTo("USA");
        shipment.setZipTo("54321");

        packageInfo.setLength(20.2);
        packageInfo.setWidth(10.5);
        packageInfo.setHeight(9.9);
        packageInfo.setWeight(100);

        shipment.setPackageInfo(packageInfo);

        Mockito.when(shipmentDAO.findShipId(1)).thenReturn(shipment);
    }

    private void whenShipmentDataWithId1(){
        shipment = shipmentService.findShipmentId(1);
    }

    private void thenReturnShipmentWithId1(){
        //Map shipmentHash = shipment.getShipmentItem();
        double rates = shipment.getRates();
        assertEquals(9.50, rates);
        String packName = shipment.getPackageName();
        assertEquals("Stub Package", packName);
        //String name = shipment.getPackageName();
    }

    //Checks if shipment can be saved.
    @Test
    void saveShipmentToHashMap() throws Exception{
        givenShipmentDataAvailable();
        whenNewShipmentCreated();
        thenCreateNewShipmentAndAddToTotalCost();
    }

    //Checks if shipments can be loaded.
    @Test
    void loadShipments() {
        givenShipmentDataAvailable();
        whenNewShipmentCreated();
        thenLoadShipments();
    }

    private void whenNewShipmentCreated(){
        shipment.setPackageId(2);
        shipment.setPackageName("Stub Package II");
    }

    private void thenCreateNewShipmentAndAddToTotalCost() throws Exception {
        Shipment shipmentTwo = shipmentService.saveEstimate(shipment);
        assertEquals(shipment, shipmentTwo);
    }

    private void thenLoadShipments() {
        List<Shipment> shipments = shipmentService.fetchAllShipments();
    }
}
