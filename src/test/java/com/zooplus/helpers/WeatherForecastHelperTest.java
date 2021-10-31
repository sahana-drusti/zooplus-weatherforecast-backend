package com.zooplus.helpers;

import com.zooplus.constants.WeatherForecastConstants;
import com.zooplus.model.WeatherForecast;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class WeatherForecastHelperTest {

    @Test
    public void test_get_date(){
        Assert.assertEquals(WeatherForecastHelper.getDate(1635316200L),"Wed, Oct 27");
    }

    @Test
    public void test_populated_country(){
        WeatherForecast forecast = new WeatherForecast();
        //when
        WeatherForecastHelper.populateCountry(forecast,"DE");
        //then
        Assert.assertEquals("DE",forecast.getCountry() );
    }

    @Test
    public void test_populate_error(){
        WeatherForecast forecast = new WeatherForecast();
        //when
        WeatherForecast actualForecast = WeatherForecastHelper.populateError(forecast,true);
        //then
        Assert.assertEquals("error",actualForecast.getErr());
    }

    @Test
    public void test_populate_error_when_weatherForecast_null(){
        WeatherForecast forecast = null;
        Assert.assertEquals("error",WeatherForecastHelper.populateError(forecast,true).getErr());
    }
}
