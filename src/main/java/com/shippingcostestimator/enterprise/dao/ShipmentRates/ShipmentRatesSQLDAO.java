package com.shippingcostestimator.enterprise.dao.ShipmentRates;

import com.shippingcostestimator.enterprise.dto.ShipmentRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ShipmentRatesSQLDAO implements IShipmentRatesDAO{

    //Map<Integer, ShipmentRate> allShipmentRates = new HashMap<>();

    @Autowired
    ShipmentRatesRepository shipmentRatesRepository;

    @Override
    public ShipmentRate saveRate(ShipmentRate shipmentRate) {
        ShipmentRate savedShipment = shipmentRatesRepository.save(shipmentRate);
        return savedShipment;
    }

//    public List<ShipmentRate> fetchShipmentRates(String rate) throws IOException {
//        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
//        IShipmentRatesRetrofitDAO shipmentRatesRetrofitDAO = retrofitInstance.create(IShipmentRatesRetrofitDAO.class);
//
//        Call<List<ShipmentRate>> ratesData = shipmentRatesRetrofitDAO.getRates(rate);
//        Response<List<ShipmentRate>> execute = ratesData.execute();
//        List<ShipmentRate> ratesFinal = execute.body();
//        return ratesFinal;
//    }
}
