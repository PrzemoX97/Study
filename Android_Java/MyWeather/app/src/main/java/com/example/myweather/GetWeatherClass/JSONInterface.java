package com.example.myweather.GetWeatherClass;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONInterface {

    @GET("weather")
    Call<WeatherGetter> getWeather(@Query("q") String city, @Query("APPID") String appid,
    @Query("units") String units);
}
