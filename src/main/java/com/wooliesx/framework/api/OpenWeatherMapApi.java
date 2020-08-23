package com.wooliesx.framework.api;

import com.wooliesx.framework.util.ConfigurationLoader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Properties;

public class OpenWeatherMapApi {

    private static String apiKey;
    private static String baseUrl;
    private Properties properties;

    public OpenWeatherMapApi() {

        properties = ConfigurationLoader.loadProperties();
        apiKey = properties.getProperty("openweathermap.apikey");
        baseUrl = properties.getProperty("openweathermap.base.url");
    }


    public Response getForcastByCity(String city) {
        String endpoint = properties.getProperty("openweathermap.forecast.endpoint");
        // prepare full api url
        // e.g. https://api.openweathermap.org/data/2.5/forecast?appid=d463c94d040c7904f9655f42f7f349dc&q=Sydney
        String apiUrl = baseUrl + endpoint + "?appid=" + apiKey + "&q=" + city;
        Response fiveDaysForcast = RestAssured.get(apiUrl);
        return fiveDaysForcast;
    }
}
