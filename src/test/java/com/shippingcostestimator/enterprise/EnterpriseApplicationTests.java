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
    private ShipmentMap shipmentMap = new ShipmentMap();

    @MockBean
    private IShipmentMapDAO shipmentMapDAO;

    @Test
    void contextLoads() {
    }

    //Checks if fetching a shipment functions correctly.
    @Test
    void fetchShipmentById_returnsShipmentWithId5() throws Exception{
        givenShipmentMapDataAvailable();
        whenShipmentWithId5Exists();
        whenShipmentDataWithId5();
        thenReturnShipmentWithId5();
    }

    private void givenShipmentMapDataAvailable(){
        Mockito.when(shipmentMapDAO.saveEstimate(shipmentMap)).thenReturn(shipmentMap);
        shipmentMapService = new ShipmentMapService(shipmentMapDAO);
    }

    private void whenShipmentWithId5Exists(){

        EasyPost.apiKey = "x";
        FromAddress fromAddress = new FromAddress();
        ToAddress toAddress = new ToAddress();
        PackageInfo parcel = new PackageInfo();


        fromAddress.setId(1);

        fromAddress.setFromStreetOne("1234 Street");
        fromAddress.setFromZip("1");
        String streetOneValue = fromAddress.getFromStreetOne();
        String originZip = fromAddress.getFromZip();

        toAddress.setId(1);
        toAddress.setToStreetOne("3421 Avenue");
        toAddress.setToZip("1");
        String streetTwoValue = toAddress.getToStreetOne();
        String destinationZip = toAddress.getToZip();

        parcel.setPackageInfoId(1);
        parcel.setWeight(32.50);


        String streetTwoValue = toAddress.getStreetOne();
        String destinationZip = toAddress.getZip();
        String streetOneValue = fromAddress.getStreetOne();
        String originZip = fromAddress.getZip();
        double weight = parcel.getWeight();



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

        shipmentMap.setId(5);
        Mockito.when(shipmentMapDAO.findShipById(5)).thenReturn(shipmentMap);
    }

    private void whenShipmentDataWithId5(){
        shipmentMap = shipmentMapService.findShipmentById(5);
    }

    private void thenReturnShipmentWithId5(){

        int id = shipmentMap.getId();
        assertEquals(5, id);
    }

    @Test
    void saveShipmentMapToHashmap() throws Exception{
        givenShipmentMapDataAvailable();
        whenNewShipmentMapCreated();
        thenCreateNewShipmentMap();
    }

    private void whenNewShipmentMapCreated() throws Exception {
        EasyPost.apiKey = "x";
        FromAddress fromAddress = new FromAddress();
        ToAddress toAddress = new ToAddress();
        PackageInfo parcel = new PackageInfo();


        fromAddress.setId(1);
        fromAddress.setStreetOne("1234 Street");
        fromAddress.setZip("1");
        toAddress.setId(1);
        toAddress.setStreetOne("3421 Avenue");
        toAddress.setZip("1");
        parcel.setPackageInfoId(1);
        parcel.setWeight(32.50);


        String streetTwoValue = toAddress.getStreetOne();
        String destinationZip = toAddress.getZip();
        String streetOneValue = fromAddress.getStreetOne();
        String originZip = fromAddress.getZip();
        double weight = parcel.getWeight();



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
    }

    private void thenCreateNewShipmentMap() throws Exception {
        ShipmentMap shipmentTwo = shipmentMapService.saveEstimate(shipmentMap);
        assertEquals(shipmentMap, shipmentTwo);
    }

    @Test
    void LoadShipmentMaps() throws Exception{
        givenShipmentMapDataAvailable();
        whenNewShipmentMapCreated();
        thenLoadShipmentMaps();
    }

    private void thenLoadShipmentMaps() {
        List<ShipmentMap> shipmentMaps = shipmentMapService.fetchAllShipments();
    }
}
