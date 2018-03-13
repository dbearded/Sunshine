package com.android.derekbearded.Sunshine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Sputnik on 3/9/2018.
 */

public class ForecastLoader extends AsyncTaskLoader<List<ForecastDay>> {
    private String url;
    public ForecastLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Nullable
    @Override
    public List<ForecastDay> loadInBackground() {
        WeatherForecast forecast = Util.loadData(url, WeatherForecast.class);
        return forecast.forecast.simpleforecast.forecastday;
    }
}
