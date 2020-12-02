package com.shippingcostestimator.enterprise;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Rate;
import com.shippingcostestimator.enterprise.dao.ShipmentRates.IShipmentRatesDAO;
import com.shippingcostestimator.enterprise.dto.*;
import com.shippingcostestimator.enterprise.service.ShipmentRates.IShipmentRatesService;
import com.shippingcostestimator.enterprise.service.ShipmentRates.ShipmentRatesService;
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

    private IShipmentRatesService shipmentRateService;
    private ShipmentRate shipmentRate = new ShipmentRate();

    @MockBean
    private IShipmentRatesDAO shipmentRateDAO;

    @Test
    void contextLoads() {
    }

    //Checks if fetching a shipment functions correctly.
    @Test
    void fetchShipmentById_returnsShipmentWithId5() throws Exception{
        givenShipmentRateDataAvailable();
        whenShipmentRateWithId5Exists();
        whenShipmentRateDataWithId5();
        thenReturnShipmentRateWithId5();
    }

    private void givenShipmentRateDataAvailable(){
        Mockito.when(shipmentRateDAO.saveRate(shipmentRate)).thenReturn(shipmentRate);
        shipmentRateService = new ShipmentRatesService(shipmentRateDAO);
    }

    private void whenShipmentRateWithId5Exists(){
        EasyPost.apiKey = "EZTK773d7864beb64671aadc6a9d64777a6boAq0h9WzLFA70as0wPnkrQ";
        ShipmentMap shipmentMap = new ShipmentMap();
        FromAddress fromAddress = new FromAddress();
        ToAddress toAddress = new ToAddress();
        PackageInfo parcel = new PackageInfo();

        fromAddress.setFromStreetOne("1234 Street");
        fromAddress.setFromZip("90231");
        String streetOneValue = fromAddress.getFromStreetOne();
        String originZip = fromAddress.getFromZip();

        toAddress.setToStreetOne("3421 Avenue");
        toAddress.setToZip("90231");
        String streetTwoValue = toAddress.getToStreetOne();
        String destinationZip = toAddress.getToZip();

        parcel.setWeight(32.50);

        double weight = parcel.getWeight();

        HashMap toAddressMap = new HashMap<String, Object>();
        toAddressMap.put("street1", streetTwoValue);
        toAddressMap.put("zip", destinationZip);

        HashMap fromAddressMap = new HashMap<String, Object>();
        fromAddressMap.put("street1", streetOneValue);
        fromAddressMap.put("zip", originZip);

        HashMap parcelMap = new HashMap<String, Object>();
        parcelMap.put("weight", weight);

        shipmentMap.setFromAddress(fromAddressMap);
        shipmentMap.setToAddress(toAddressMap);
        shipmentMap.setParcel(parcelMap);

        Map<String, Object> shipment = new HashMap<String, Object>();
        shipment.put("to_address", shipmentMap.getToAddress());
        shipment.put("from_address", shipmentMap.getFromAddress());
        shipment.put("parcel", shipmentMap.getParcel());

        //EASYPOST logic
        com.easypost.model.Shipment shipmentModel = null;
        //Create easypost shipment
        try {
            shipmentModel = com.easypost.model.Shipment.create(shipment);
        } catch (EasyPostException e) {
            e.printStackTrace();
        }
        ShipmentRate shipmentRate = new ShipmentRate();
        //Parse Shipment object's rates and save each one to an object.
        try{
            var rates = shipmentModel.getRates();

            //For each rate, save to an object.
            for(Rate rate : rates){

                shipmentRate.setObject(fromAddress.getFromStreetOne());
                shipmentRate.setCarrier(rate.getCarrier());
                shipmentRate.setService(rate.getService());
                shipmentRate.setRate(rate.getRate());
                shipmentRate.setId(5);

                shipmentRateService.saveRate(shipmentRate);
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        Mockito.when(shipmentRateService.findRate(5)).thenReturn(shipmentRate);
    }

    private void whenShipmentRateDataWithId5(){
        shipmentRate = shipmentRateService.findRate(5);
    }

    private void thenReturnShipmentRateWithId5(){

        int id = shipmentRate.getId();
        assertEquals(5, id);
    }

    @Test
    void LoadShipmentMaps() throws Exception{
        givenShipmentRateDataAvailable();
        whenShipmentRateWithId5Exists();
        thenLoadShipmentMaps();
    }

    private void thenLoadShipmentMaps() {
        List<ShipmentRate> shipmentRates = shipmentRateService.findAllRates();
    }
}
