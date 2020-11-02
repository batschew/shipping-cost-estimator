package com.shippingcostestimator.enterprise.dao;

import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

@Repository
public class ShippingCostDAO implements IShippingCostDAO{
    @Override
    public String fetchServiceLevel(String apiKey, int shipmentId) throws IOException {
        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
        IShippingCostRetrofitDAO shippingCostRetrofitDAO = retrofitInstance.create(IShippingCostRetrofitDAO.class);

        Call<String> ratesData = shippingCostRetrofitDAO.getServiceLevel(apiKey);
        Response<String> execute = ratesData.execute();
        String serviceLevel = execute.body();

        return serviceLevel;
    }

    @Override
    public String fetchRates(String apiKey, int shipmentId) throws IOException {
        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
        IShippingCostRetrofitDAO shippingCostRetrofitDAO = retrofitInstance.create(IShippingCostRetrofitDAO.class);

        Call<String> ratesData = shippingCostRetrofitDAO.getServiceLevel(apiKey);
        Response<String> execute = ratesData.execute();
        String rates = execute.body();

        return rates;
    }

    @Override
    public String fetchEstArrival(String apiKey) throws IOException {
        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
        IShippingCostRetrofitDAO shippingCostRetrofitDAO = retrofitInstance.create(IShippingCostRetrofitDAO.class);

        String estArrival = "";
        return estArrival;
    }
}
