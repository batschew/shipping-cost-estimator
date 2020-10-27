package com.shippingcostestimator.enterprise.dao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface IShippingCostRetrofitDAO {
    @GET("shipments/rates/service_level")
    Call<String> getServiceLevel(@Header("Authorization") String apiKey);

    @GET("shipments/rates")
    Call<String> getRates(@Header("Authorization") String apiKey);

    @GET("trackers/trk_{id}/est_deliver_date")
    Call<String> getEstArrival(@Header("Authorization") String apiKey);
}
