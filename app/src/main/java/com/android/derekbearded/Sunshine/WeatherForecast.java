package com.android.derekbearded.Sunshine;

import java.util.List;

/**
 * Created by Sputnik on 3/12/2018.
 */

public class WeatherForecast {
    public Forecast forecast;

    public static class Forecast {
        public SimpleForecast simpleforecast;
    }

    public static class SimpleForecast {
        public List<ForecastDay> forecastday;
    }
}
