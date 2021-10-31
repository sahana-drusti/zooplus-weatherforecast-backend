package com.zooplus.helpers;

import com.zooplus.constants.WeatherForecastConstants;
import com.zooplus.model.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeatherForecastHelper {
    private static final Logger logger = LoggerFactory.getLogger(WeatherForecastHelper.class);

    /**
     * get formatted date
     * @param dt
     * @return
     */
    public static String getDate(Long dt){
        String methodName = "getDate";
        logger.debug("entering into class " + WeatherForecastHelper.class.getName() + " of method:" + methodName + " having input argument:" + dt);
        Date d = new Date(dt*1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat(WeatherForecastConstants.DATE_FORMAT_PATTERN);
        String formattedDate = dateFormat.format(d);
        logger.debug("exiting method:" + methodName + " of class:" + WeatherForecastHelper.class.getName() + " with response:" + formattedDate);
        return formattedDate;
    }

    /**
     * get current time in a given timezone
     * @param weatherForecast
     * @return
     */
    public static String getCurrentDateToTimeZone(WeatherForecast weatherForecast){
        String methodName = "getCurrentDateToTimeZone";
        logger.debug("entering into class " + WeatherForecastHelper.class.getName() + " of method:" + methodName + " having input argument:" + weatherForecast.getTimezone());
        Date d= new Date();
        String timeZone = weatherForecast.getTimezone();
        SimpleDateFormat dateFormater = new SimpleDateFormat(WeatherForecastConstants.DATE_FORMAT_WITH_AA);
        final List<String> timeZonesInCountry = Stream.of(TimeZone.getAvailableIDs())
                .filter(zoneId -> zoneId.startsWith(timeZone)).collect(Collectors.toList());
        if(timeZonesInCountry.size() > 0){
            dateFormater.setTimeZone(TimeZone.getTimeZone(timeZonesInCountry.get(0)));
        }
        String formattedDate = dateFormater.format(d);
        weatherForecast.getCurrent().setDt(formattedDate);
        logger.debug("exiting method:" + methodName + " of class:" + WeatherForecastHelper.class.getName() + " with response:" + formattedDate);
        return formattedDate;
    }

    /**
     * populates country to weatherForecast
     * @param weatherDetails
     * @param country
     */
    public static void populateCountry(WeatherForecast weatherDetails, String country) {
        String methodName = "populateCountry";
        logger.debug("entering into class " + WeatherForecastHelper.class.getName() + " of method:" + methodName + " having input argument:" + country);
        if (weatherDetails != null && !country.isEmpty()) {
            weatherDetails.setCountry(country);
        }
        logger.debug("exiting method:" + methodName + " of class:" + WeatherForecastHelper.class.getName() + " with response:" + weatherDetails);
    }

    /**
     * populates formatted time in weatherforecast
     * @param weatherDetails
     */
    public static void populateTimeZone(WeatherForecast weatherDetails) {
        String methodName = "populateTimeZone";
        logger.debug("entering into class " + WeatherForecastHelper.class.getName() + " of method:" + methodName + " having input argument:" + weatherDetails);
        if (weatherDetails != null && weatherDetails.getTimezone() != null) {
            WeatherForecastHelper.getCurrentDateToTimeZone(weatherDetails);
        }
        logger.debug("exiting method:" + methodName + " of class:" + WeatherForecastHelper.class.getName() + " with response:" + weatherDetails);
    }

    /**
     * populates error in weatherforecast
     * @param weatherDetails
     * @param error
     * @return
     */
    public static WeatherForecast populateError(WeatherForecast weatherDetails, boolean error) {
        String methodName = "populateError";
        logger.debug("entering into class " + WeatherForecastHelper.class.getName() + " of method:" + methodName + " having input argument:" + error);
        if (weatherDetails == null) {
            error = true;
        }
        if (error) {
            weatherDetails = new WeatherForecast();
            weatherDetails.setErr("error");
        }
        logger.debug("entering into class " + WeatherForecastHelper.class.getName() + " of method:" + methodName + " having input argument:" + error);
        return weatherDetails;
    }
}
