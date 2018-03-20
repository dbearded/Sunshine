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
    private String apiKey;
    private int zipCode;
    public ForecastLoader(@NonNull Context context, String apiKey, int zipCode) {
        super(context);
        this.apiKey = apiKey;
        this.zipCode = zipCode;
    }

    @Nullable
    @Override
    public List<ForecastDay> loadInBackground() {
        return Util.loadWeather(apiKey, zipCode);
    }
}
