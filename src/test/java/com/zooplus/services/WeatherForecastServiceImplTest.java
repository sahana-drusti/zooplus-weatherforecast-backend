package com.zooplus.services;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.zooplus.model.LongLat;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
public class WeatherForecastServiceImplTest {
    WireMockRule rule = new WireMockRule(8089);
    @InjectMocks
    WeatherForecastServiceImpl service;
    public void setUp(){
        stubFor(get("http://localhost:8089?q=Munich&limit=1&appid=apiKey")
                .willReturn(ok()
                        .withStatus(200)
                        .withBody("<response>SUCCESS</response>")));
    }
    @Test
    public void test_long_lat_result(){
        //when
       LongLat longLat = service.getLongitudeAndLatitudeForCity("Munich","http://localhost:8089?","1","apiKey");
        //then
        Assert.assertEquals("DE",longLat.getCountry());
        Assert.assertEquals("2.87", longLat.getLat());
        Assert.assertEquals("1.23",longLat.getLon());

    }

}
