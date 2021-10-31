package com.zooplus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast {
    CurrentWeather current;
    DailyForecast[] daily = new DailyForecast[5];
    String country;
    String err;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "current=" + current +
                ", daily=" + Arrays.toString(daily) +
                ", country='" + country + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    String timezone;


    public void setDaily(DailyForecast[] daily) {
        this.daily = daily;
    }

    public DailyForecast[] getDaily() {
        return daily;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }
}
