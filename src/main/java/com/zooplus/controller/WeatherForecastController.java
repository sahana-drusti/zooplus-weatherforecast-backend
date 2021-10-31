package com.zooplus.controller;

import com.zooplus.model.LongLat;
import com.zooplus.model.WeatherForecast;
import com.zooplus.services.WeatherForecastService;
import com.zooplus.services.WeatherForecastServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class WeatherForecastController {

    @Autowired
    private Environment env;
    private static final Logger logger = LoggerFactory.getLogger(WeatherForecastController.class);

    @Autowired
    WeatherForecastService service;

    @GetMapping("/weather/{city}")
    @CrossOrigin
    @Value("${longitude.latitude.url},${weather.forecast.url},${apiKey},${limit}")
    public WeatherForecast getWeatherDetails(@PathVariable(value = "city") String city) {
        String methodName = "getWeatherDetails";
        logger.debug("entering into class " + getClass().getName() + " of method:" + methodName + " having input argument:" + city);
        String longLatBaseURL = env.getProperty("longitude.latitude.url");
        String appId = env.getProperty("apiKey");
        String limit = env.getProperty("limit");
        String oneCallBaseURL = env.getProperty("weather.forecast.url");
        LongLat longLat = service.getLongitudeAndLatitudeForCity(city, longLatBaseURL, limit, appId);
        WeatherForecast weatherForecast = service.getWeather(longLat,oneCallBaseURL,appId);
        return weatherForecast;
    }

}
