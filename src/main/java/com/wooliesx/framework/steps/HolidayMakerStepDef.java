package com.wooliesx.framework.steps;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wooliesx.framework.api.OpenWeatherMapApi;
import com.wooliesx.framework.api.vo.ForcastModel;
import com.wooliesx.framework.api.vo.Temperature;
import com.wooliesx.framework.api.vo.WeatherConditions;
import com.wooliesx.framework.util.DateUtil;
import com.wooliesx.framework.util.TemperatureUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HolidayMakerStepDef {

    private static ForcastModel forcastModel;
    private static List<WeatherConditions> thursdayWeatherConditions;

    @Given("^I like to holiday in \"([^\"]*)\"$")
    public void iLikeToHolidayIn(String city) {
        OpenWeatherMapApi openWeatherMapApi = new OpenWeatherMapApi();
        Response response = openWeatherMapApi.getForcastByCity(city);
        String responseString = response.getBody().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            forcastModel = objectMapper.readValue(responseString, ForcastModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("^I only like to holiday on Thursdays$")
    public void iOnlyLikeToHolidayOnThursdays() {
        //from the forcastModel get all the thursdays
        //40 elements - 8 for each days
        List<WeatherConditions> weatherConditionsList = forcastModel.getWeatherConditionsList();
        thursdayWeatherConditions = new ArrayList<>();
        for (WeatherConditions weatherCondition : weatherConditionsList) {
            String dateAsString = weatherCondition.getDt_txt(); // e.g. format "2020-08-22 06:00:00"
            boolean isThursday = DateUtil.isThursday(dateAsString);
            if (isThursday) {
                thursdayWeatherConditions.add(weatherCondition);
            }
        }
    }

    @When("^I look up the weather forecast$")
    public void iLookUpTheWeatherForecast() {
    }

    @Then("^I receive the weather forecast$")
    public void iReceiveTheWeatherForecast() {
    }

    @And("^the temperature is warmer than \"([^\"]*)\" degrees$")
    public void theTemperatureIsWarmerThanDegrees(int tempDegrees) {
        boolean isTempFavourable = true;
        for (WeatherConditions weatherConditions : thursdayWeatherConditions) {
            Temperature temperature = weatherConditions.getTemperature();
            float tempMinInKelvin = temperature.getTemp_min();
            float tempInCelsius = TemperatureUtil.convertToCelsius(tempMinInKelvin);
            if (tempInCelsius < tempDegrees) {
                isTempFavourable = false;
                break;
            }
        }
        if (!isTempFavourable) {
            System.out.println("No upcoming Thursday is suitable for holiday");
        }
        Assert.assertTrue(isTempFavourable, "Temp is favourable and warmer than " + tempDegrees);
    }
}
