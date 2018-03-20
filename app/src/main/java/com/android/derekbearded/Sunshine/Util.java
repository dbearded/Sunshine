package com.android.derekbearded.Sunshine;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Sputnik on 3/9/2018.
 */

public class Util {

    public static final String BASE_URL = "http://api.wunderground.com/";

    private static Retrofit retrofit;

    private static void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    public interface WeatherSerivce {
        @GET("api/{key}/astronomy/forecast10day/q/{zipCode}.json")
        Call<WeatherForecast> weatherForecast(@Path("key") String key, @Path("zipCode") int zipCode);
    }

    public static List<ForecastDay> loadWeather(String key, int zipCode) {
        if (retrofit == null){
            initRetrofit();
        }
        WeatherSerivce service = retrofit.create(WeatherSerivce.class);
        Call<WeatherForecast> call = service.weatherForecast(key, zipCode);

        List<ForecastDay> days = null;
        try {
            WeatherForecast forecast = call.execute().body();
            days = forecast.forecast.simpleforecast.forecastday;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return days;
    }
}
