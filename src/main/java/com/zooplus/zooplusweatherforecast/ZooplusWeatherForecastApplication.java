package com.zooplus.zooplusweatherforecast;

import com.zooplus.controller.WeatherForecastController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.zooplus.*"})
@EnableCaching
public class ZooplusWeatherForecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZooplusWeatherForecastApplication.class, args);
    }

}
