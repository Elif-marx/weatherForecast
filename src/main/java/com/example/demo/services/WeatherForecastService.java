package com.example.demo.services;

import com.example.demo.models.Weather;
import com.example.demo.models.responses.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherForecastService {
    @Value("${openweathermap.api.key}")
    private String apiKey;

    public Weather getWeatherData(String city) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        OpenWeatherResponse response = restTemplate.getForObject(apiUrl, OpenWeatherResponse.class);

       /* Double maxTemperature = JsonPath.read(response, "$.daily[0].temp.max");

        Double feelsLikeTemperature = JsonPath.read(response, "$.current.feels_like");

        Integer humidity = JsonPath.read(response, "$.current.humidity");*/

        Weather weatherForecast = new Weather();
        Weather weather = new Weather();
        weather.setMaxTemperature(response.getDaily().get(0).getTemp().getMax());
        weather.setFeelsLikeTemperature(response.getCurrent().getFeelslike());
        weather.setHumidity(response.getCurrent().getHumidity());

        return weatherForecast;
    }

}
