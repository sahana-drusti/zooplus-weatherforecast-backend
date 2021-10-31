package com.zooplus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zooplus.helpers.WeatherForecastHelper;

import java.util.List;
import java.util.Map;

public class DailyForecast {

    String dt;
    String minTemp;
    String maxTemp;
    List<Weather> weather;

    @JsonProperty("temp")
    private void unpackMaxFromNestedObject(Map<String, String> temp) {
        maxTemp = temp.get("max");
        minTemp = temp.get("min");
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getDt() {
        return WeatherForecastHelper.getDate(Long.valueOf(dt));
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "DailyForecast{" +
                "dt='" + dt + '\'' +
                ", weather=" + weather +
                ",temp{"+
                ", minTemp='" + minTemp + '\'' +
                ", maxTemp='" + maxTemp + '\'' +
                '}'+
                '}';
    }
}
