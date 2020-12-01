package com.shippingcostestimator.enterprise.dao.PackageInfo;

import com.shippingcostestimator.enterprise.dao.RetrofitClientInstance;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

@Repository
public class PackageInfoDAO implements IPackageInfoDAO {
    @Override
    public String fetchPredefinedPackage(String apiKey, int shipmentId) throws IOException {
        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
        IPackageInfoRetrofitDAO packageInfoRetrofitDAO = retrofitInstance.create(IPackageInfoRetrofitDAO.class);

        Call<String> parcelData = packageInfoRetrofitDAO.getPredefinedPackage(apiKey);
        Response<String> execute = parcelData.execute();
        String predefinedPackage = execute.body();

        return predefinedPackage;
    }
}
