package com.zooplus.services;

import com.zooplus.helpers.WeatherForecastHelper;
import com.zooplus.model.LongLat;
import com.zooplus.model.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherForecastServiceImpl implements WeatherForecastService {

    @Autowired
    Environment environment;
    private static final Logger logger = LoggerFactory.getLogger(WeatherForecastServiceImpl.class);

    public WeatherForecastServiceImpl(){}


    /**
     * get longitude and latitude of city
     * @param city
     * @return
     */
    @Override
    @Cacheable("cityDetails")
    public LongLat getLongitudeAndLatitudeForCity(String city, String baseURL, String limit, String appId) {

            String methodName = "getLongitudeAndLatitude";
            logger.debug("entering into class " + getClass().getName() + " of method:" + methodName + " having input argument:" + city);
            LongLat longLat = new LongLat();
            String url = baseURL + "q=" + city + "&limit=" + limit + "&appid=" + appId;
            System.out.println(url);
            ResponseEntity<LongLat[]> response = null;
            try {
                response = new RestTemplate().getForEntity(url, LongLat[].class);
                if (response != null && response.getStatusCodeValue() == 200) {
                    LongLat[] cityDetails = response.getBody();
                    if (cityDetails != null && cityDetails.length > 0) {
                        longLat = cityDetails[0];
                        logger.debug("response from url:" + url + " with limit:" + limit + " and city:" + city + "is " + longLat);
                    }
                }
            } catch (Exception exception) {
                if (response != null) {
                    int statusCode = response.getStatusCodeValue();
                    logger.error("Exception throwed while fetching response at classname:" + getClass().getName() + ": method: " + methodName + " error code:" + statusCode + " with exception:" + exception);
                }
            }
            logger.debug("exiting method:" + methodName + " of class:" + getClass().getName());
            return longLat;

    }



@Override
    public WeatherForecast getWeather(LongLat longLat, String baseURL, String appId) {
        String methodName = "getWeather";
        logger.debug("entering to class:"+getClass().getName()+" inside method:"+methodName+" having argument 1:"+longLat+" arg 2:"+baseURL+" arg 3:"+appId);
        boolean error = false;
        WeatherForecast weatherDetails = null;
        String country = longLat.getCountry();
        String longitude = longLat.getLon();
        String latitude = longLat.getLat();
        if (longitude != null && latitude != null) {
            String url = baseURL + "lat=" + latitude + "&lon=" + longitude + "&units=metric&appid=" + appId;
            ResponseEntity<WeatherForecast> response = null;
            try {
                response = new RestTemplate().getForEntity(url, WeatherForecast.class);
                if (response != null && response.getStatusCodeValue() == 200) {
                    weatherDetails = response.getBody();
                    logger.debug("response from url:" + url + " having longitude:" + longitude + " and latitude:" + latitude + "is " + weatherDetails);
                }
                WeatherForecastHelper.populateCountry(weatherDetails, country);
                WeatherForecastHelper.populateTimeZone(weatherDetails);
                weatherDetails = WeatherForecastHelper.populateError(weatherDetails, error);
            } catch (Exception exception) {
                if (response != null) {
                    int statusCode = response.getStatusCodeValue();
                    logger.error("Exception throwed while fetching response at classname:" + getClass().getName() + ": method: " + methodName + " error code:" + statusCode + " with exception:" + exception);
                }
            }
        } else {
            weatherDetails = WeatherForecastHelper.populateError(weatherDetails, error);
        }
        return weatherDetails;
    }

}
