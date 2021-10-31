package com.zooplus.controller;

import com.zooplus.model.LongLat;
import com.zooplus.model.Weather;
import com.zooplus.model.WeatherForecast;
import com.zooplus.services.WeatherForecastService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.env.Environment;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"limit=1"})
public class WeatherForecastControllerTest {

    @Value("${limit}")
    String limit;

    String city = "Munich";

    @Mock
    WeatherForecastService service;

    @Mock
    Environment env;
    @InjectMocks
    WeatherForecastController controller;


    @Before
    public void setUp(){
        WeatherForecast weatherForecast =  new WeatherForecast();
        weatherForecast.setCountry("DE");
        Mockito.when(env.getProperty("longitude.latitude.url")).thenReturn("http://localhost::8080");
        Mockito.when(env.getProperty("weather.forecast.url")).thenReturn("http://localhost::8080");
        Mockito.when(env.getProperty("limit")).thenReturn("1");
        Mockito.when(env.getProperty("apiKey")).thenReturn("apiKey");
        Mockito.when(service.getLongitudeAndLatitudeForCity(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(new LongLat());
        Mockito.when(service.getWeather(Mockito.any(LongLat.class),Mockito.anyString(),Mockito.anyString())).thenReturn(weatherForecast);
    }

    @Test
    public void testPropertyInject(){
        Assert.assertEquals("1",limit);
    }

    @Test
    public void test_longitudeAndLatitude_called(){
        //when
        WeatherForecast weather = controller.getWeatherDetails(city);

        //then
        Mockito.verify(service, Mockito.times(1)).getLongitudeAndLatitudeForCity(Mockito.eq(city), Mockito.eq("http://localhost::8080") ,Mockito.eq(limit),Mockito.eq("apiKey"));

    }

    @Test
    public void test_getWeather_called(){
        //when
        WeatherForecast weather = controller.getWeatherDetails(city);

        //then
        Assert.assertEquals("DE",weather.getCountry());

    }

}
