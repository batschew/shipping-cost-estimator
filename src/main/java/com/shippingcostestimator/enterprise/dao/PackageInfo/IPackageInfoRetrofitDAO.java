package com.shippingcostestimator.enterprise.dao.PackageInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface IPackageInfoRetrofitDAO {
    @GET("parcels/prcl_{packageId}/predefined_package")
    Call<String> getPredefinedPackage(@Header("Authorization") String apiKey);
}
