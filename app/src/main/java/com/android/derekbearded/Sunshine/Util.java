package com.android.derekbearded.Sunshine;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Sputnik on 3/9/2018.
 */

public class Util {

    public static final String WEATHER_FORMAT = ".json";
    public static final String BASE_URL = "http://api.wunderground.com/api/";
    public static final String PATH_URL = "/astronomy/forecast10day/q/";

    public static final OkHttpClient httpClient = new OkHttpClient();

    public static String getWeatherUrl(int zipCode, String apiKey) {
        return String.format("%s%s%s", BASE_URL + apiKey + PATH_URL, zipCode, WEATHER_FORMAT);
    }

    public static <T> T loadData(String url, Class<T> cls) {
        T t = null;
        Moshi moshi = new Moshi.Builder().build();

        JsonReader reader = JsonReader.of(makeHttpRequest(url));
        try {
            JsonAdapter<T> adapter = moshi.adapter(cls);
            t = adapter.fromJson(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static okio.BufferedSource makeHttpRequest(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()){
                throw new IOException("Response code: " + response.code() +" Response not successful at: " + url);
            }
            return response.body().source();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
