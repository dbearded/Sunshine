package com.android.derekbearded.Sunshine;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        System.out.println(Util.getWeatherUrl(21742));
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, hh:mm a", Locale.US);
//        dateFormat.format(1520653630000l);
//        System.out.println(DateFormat.getDateInstance().format(1520653630000l));
        System.out.println(dateFormat.format(1520653630000l));
    }
}