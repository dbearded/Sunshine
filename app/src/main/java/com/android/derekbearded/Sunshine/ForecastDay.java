package com.android.derekbearded.Sunshine;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Sputnik on 3/9/2018.
 */

class ForecastDay {
    public Date date;
    public Temperature high, low;
    public String conditions, icon, icon_url;
    public Wind maxwind, avewind;
    public int avehumidity, maxhumidity, minhumidity;

    static class Date {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d", Locale.US);
        public long epoch;
        public int day, month, year, hour, min, sec;
        public String tz_short, tz_long;

        private String dateStr;

        @Override
        public String toString() {
            if (dateStr == null && epoch != 0) {
                dateStr = dateFormat.format(epoch * 1000l);
            }
            return dateStr;
        }
    }

    static class Temperature {
        public int fahrenheit, celsius;
    }

    static class Wind {
        public int mph, kph, degrees;
        public String dir;

        @Override
        public String toString() {
            return mph + " mph " + dir;
        }
    }
}