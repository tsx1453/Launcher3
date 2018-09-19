package com.android.launcher3.custompage.network;

import com.android.launcher3.custompage.bean.LocationBean;
import com.android.launcher3.custompage.bean.WeatherBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetWorkRequest {

    @GET("https://restapi.amap.com/v3/ip?key=4f0fa168717c044e22af1311d5386cf6")
    Call<LocationBean> getLocation();

    @GET("https://restapi.amap.com/v3/ip?key=4f0fa168717c044e22af1311d5386cf6")
    Call<ResponseBody> getLocationStr();

    @GET("https://free-api.heweather.com/s6/weather/now?key=b558055f8ce74a97855043e67885c96e")
    Call<WeatherBean> getWeather(@Query("location") String locate);

}
