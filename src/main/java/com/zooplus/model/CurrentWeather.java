package com.zooplus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zooplus.helpers.WeatherForecastHelper;
import org.springframework.context.annotation.Bean;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {

        String feels_like;
        String temp;
        List<Weather> weather;
        String dt;

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "feels_like='" + feels_like + '\'' +
                ", temp='" + temp + '\'' +
                ", weather=" + weather +
                ", dt='" + dt + '\'' +
                '}';
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(String feels_like) {
        this.feels_like = feels_like;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }


}
