package com.zooplus.services;

import com.zooplus.model.LongLat;
import com.zooplus.model.WeatherForecast;


public interface WeatherForecastService {
    public LongLat getLongitudeAndLatitudeForCity(String city, String baseURL, String limit, String appId);
    public WeatherForecast getWeather(LongLat longLat, String baseURL, String appId);
}
