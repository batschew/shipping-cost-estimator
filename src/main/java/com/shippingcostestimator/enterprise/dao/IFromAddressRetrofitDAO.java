package com.shippingcostestimator.enterprise.dao;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IFromAddressRetrofitDAO {
    @GET("/addresses")
    void getFromAddress(@Query("Address") String Address);
}
