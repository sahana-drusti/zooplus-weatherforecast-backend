package com.zooplus.services;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.zooplus.model.LongLat;
import com.zooplus.model.WeatherForecast;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
public class WeatherForecastServiceImplTest {

    @InjectMocks
    WeatherForecastServiceImpl service;
    WireMockServer server = new WireMockServer(8089);

    @Before
    public void setUp(){

        configureFor("localhost",8089);
        server.start();
        //urlEqualTo("http://localhost:8089?q=Munich&limit=1&appid=apiKey")
        stubFor(get(urlMatching("^.*direct.*$"))
                .willReturn(okJson("[\n" +
                        "{\n" +
                        "\t\"country\":\"DE\",\n" +
                        "\t\"lon\":\"23.87\",\n" +
                        "\t\"lat\":\"34.67\"\n" +
                        "}\n" +
                        "]")
                        .withStatus(200)));
        stubFor(get(urlEqualTo("/onecall?lat=23.67&lon=56.87&units=metric&appid=apiKey"))
                .willReturn(okJson("{\n" +
                        "\"country\":\"DE\"\n" +
                        "}")
                .withStatus(200))
        );


    }
    @Test
    public void test_long_lat_result(){
        //when
       LongLat longLat = service.getLongitudeAndLatitudeForCity("Munich","http://localhost:8089/direct?","1","apiKey");
        //then
        Assert.assertEquals("DE",longLat.getCountry());
        Assert.assertEquals("23.87",longLat.getLon());
        Assert.assertEquals("34.67",longLat.getLat());

    }

    @Test
    public void test_when_api_returns_404(){
        LongLat longLat = service.getLongitudeAndLatitudeForCity("Munich","http://localhost:8080/direct?","1","apiKey");
        Assert.assertNull(longLat.getCountry());
    }

    @Test
    public void test_get_weather_success_with_lonLat(){
        LongLat longLat = new LongLat();
        longLat.setCountry("DE");
        longLat.setLat("23.67");
        longLat.setLon("56.87");
        WeatherForecast weatherForecast = service.getWeather(longLat,"http://localhost:8089/onecall?","apiKey");
        Assert.assertEquals("DE",weatherForecast.getCountry());
    }


@After
public void postProcess(){
    server.stop();
}
}
