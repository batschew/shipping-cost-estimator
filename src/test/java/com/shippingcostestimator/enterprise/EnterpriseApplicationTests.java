package com.shippingcostestimator.enterprise;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Rate;
import com.easypost.model.Shipment;
import com.shippingcostestimator.enterprise.dao.IShipmentMapDAO;
import com.shippingcostestimator.enterprise.dto.FromAddress;
import com.shippingcostestimator.enterprise.dto.PackageInfo;
import com.shippingcostestimator.enterprise.dto.ShipmentMap;
import com.shippingcostestimator.enterprise.dto.ToAddress;
import com.shippingcostestimator.enterprise.service.IShipmentMapService;
import com.shippingcostestimator.enterprise.service.ShipmentMapService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class EnterpriseApplicationTests {

    private IShipmentMapService shipmentMapService;
    private ShipmentMap shipment = new ShipmentMap();
    private PackageInfo packageInfo = new PackageInfo();

    @MockBean
    private IShipmentMapDAO shipmentMapDAO;

    @Test
    void contextLoads() {
    }

    //Checks if fetching a shipment functions correctly.
    @Test
    void fetchShipmentById_returnsShipmentWithId0() throws Exception{
        givenShipmentDataAvailable();
        whenShipmentWithId0Exists();
        whenShipmentDataWithId0();
        thenReturnShipmentWithId0();
    }

    private void givenShipmentDataAvailable(){
        Mockito.when(shipmentMapDAO.saveEstimate(shipment)).thenReturn(shipment);
        shipmentMapService = new ShipmentMapService(shipmentMapDAO);
    }

    private void whenShipmentWithId0Exists(){

        EasyPost.apiKey = "x";
        ShipmentMap shipmentMap = new ShipmentMap();
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

        shipmentMap.setFromAddress(fromAddressMap);
        shipmentMap.setToAddress(toAddressMap);
        shipmentMap.setParcel(parcelMap);

        Map<String, Object> shipment = new HashMap<String, Object>();
        shipment.put("to_address", shipmentMap.getToAddress());
        shipment.put("from_address", shipmentMap.getFromAddress());
        shipment.put("parcel", shipmentMap.getParcel());



        //After this point, we make the shipment itself and send it through the API.
        Shipment shipmentTwo = null;
        Rate rate = null;
        try {
            shipmentTwo = com.easypost.model.Shipment.create(shipment);
            rate = shipmentTwo.lowestRate();
        } catch (EasyPostException e) {
            e.printStackTrace();
        }

        Mockito.when(shipmentMapDAO.findShipById(0)).thenReturn(shipmentMap);
    }

    private void whenShipmentDataWithId0(){
        shipment = shipmentMapService.findShipmentById(0);
    }

    private void thenReturnShipmentWithId0(){

        int id = shipment.getId();
        assertEquals(0, id);
    }
//
//    //Checks if shipment can be saved.
//    @Test
//    void saveShipmentToHashMap() throws Exception{
//        givenShipmentDataAvailable();
//        whenNewShipmentCreated();
//        thenCreateNewShipmentAndAddToTotalCost();
//    }
//
//    //Checks if shipments can be loaded.
//    @Test
//    void loadShipments() {
//        givenShipmentDataAvailable();
//        whenNewShipmentCreated();
//        thenLoadShipments();
//    }
//
//    private void whenNewShipmentCreated(){
//        shipment.setPackageId(2);
//        shipment.setPackageName("Stub Package II");
//    }
//
//    private void thenCreateNewShipmentAndAddToTotalCost() throws Exception {
//        Shipment shipmentTwo = shipmentService.saveEstimate(shipment);
//        assertEquals(shipment, shipmentTwo);
//    }
//
//    private void thenLoadShipments() {
//        List<Shipment> shipments = shipmentService.fetchAllShipments();
//    }
}
