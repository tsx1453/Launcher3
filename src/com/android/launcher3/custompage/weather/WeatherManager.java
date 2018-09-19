package com.android.launcher3.custompage.weather;

import android.support.annotation.NonNull;

import com.android.launcher3.Utilities;
import com.android.launcher3.custompage.bean.LocationBean;
import com.android.launcher3.custompage.network.NetWorkRequest;
import com.android.launcher3.custompage.bean.WeatherBean;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherManager {

    public String getLocation() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restapi.amap.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetWorkRequest netWorkRequest = retrofit.create(NetWorkRequest.class);
        Call<LocationBean> locationBeanCall = netWorkRequest.getLocation();
        Response<LocationBean> response = null;
        try {
            response = locationBeanCall.execute();
//            Log.d("mylauncher3",)
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response == null ? "error" : response.body().getCity();
    }

    public void getWeather(@NonNull final WeatherGetListener listener) {
        Utilities.THREAD_POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                String city = getLocation();
                if (city.equals("error")) {
                    listener.onFailed("Location error");
                }
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://free-api.heweather.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                NetWorkRequest request = retrofit.create(NetWorkRequest.class);
                Call<WeatherBean> call = request.getWeather(city);
                call.enqueue(new Callback<WeatherBean>() {
                    @Override
                    public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                        listener.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<WeatherBean> call, Throwable t) {
                        listener.onFailed(t.getMessage());
                    }
                });
            }
        });

    }

    public String getLocationStr() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restapi.amap.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetWorkRequest netWorkRequest = retrofit.create(NetWorkRequest.class);
        Call<ResponseBody> responseBodyResponse = netWorkRequest.getLocationStr();
        Response<ResponseBody> responseBodyResponse1 = null;
        try {
            responseBodyResponse1 = responseBodyResponse.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBodyResponse1 == null ? "error" : responseBodyResponse1.body().string();
    }

    public interface WeatherGetListener {
        void onSuccess(WeatherBean bean);

        void onFailed(String s);
    }

}
