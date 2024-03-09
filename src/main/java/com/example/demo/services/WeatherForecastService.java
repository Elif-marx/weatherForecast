package com.example.demo.services;

import com.example.demo.models.Coordinates;
import com.example.demo.models.Weather;

import com.example.demo.models.WeatherList;
import com.example.demo.models.responses.MyResponse;
import com.example.demo.models.responses.OpenWeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherForecastService {
    @Value("${openweathermap.api.key}")
    private String API_KEY;

    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    public MyResponse getWeatherData(String city) {
        String apiUrl = BASE_URL + "forecast?q=" + city + "&appid=" + API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        ObjectMapper deserializer = new ObjectMapper();
        OpenWeatherResponse openWeatherResponse = new OpenWeatherResponse();

        try {
            openWeatherResponse = deserializer.readValue(response, OpenWeatherResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return get48hoursForecast(openWeatherResponse.getList());
    }

    public MyResponse get48hoursForecast(List<WeatherList> weatherLists){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endDateTime = now.plusHours(48);

        List<WeatherList> twoDaysList = new ArrayList<>();

        for (WeatherList weatherList : weatherLists) {
            LocalDateTime listDateTime = weatherList.getDateTime();

            if (listDateTime.isAfter(now) && listDateTime.isBefore(endDateTime)) {
                twoDaysList.add(weatherList);
            }
        }
        MyResponse myResponse=new MyResponse(now,twoDaysList);
        return myResponse;
    }

/*
    public List<Weather> getWeatherForecast(String city) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/onecall?lat={LATITUDE}&lon={LONGITUDE}&exclude=current,minutely,daily&appid={API_KEY}";
        // Şehir adından enlem ve boylam bilgilerini almak için Geocoding API


        apiUrl = apiUrl.replace("{LATITUDE}", String.valueOf(latitude))
                .replace("{LONGITUDE}", String.valueOf(longitude))
                .replace("{API_KEY}", apiKey);

        // OpenWeatherMap One Call API'ye çağrı yap
        String response = restTemplate.getForObject(apiUrl, String.class);
        JSONObject jsonResponse = new JSONObject(response);

        JSONArray hourlyForecasts = jsonResponse. ("hourly");

        List<Weather> weatherForecasts = new ArrayList<>();

        for (int i = 0; i < Math.min(48, hourlyForecasts.length()); i++) {
            JSONObject forecast = hourlyForecasts.getJSONObject(i);
            JSONObject mainObject = forecast.getJSONObject("temp");

            Double maxTemperature = mainObject.getDouble("max");
            Double feelsLikeTemperature = forecast.getDouble("feels_like");
            Integer humidity = forecast.getInt("humidity");

            Weather weatherForecast = new Weather(maxTemperature, feelsLikeTemperature, humidity);
            weatherForecasts.add(weatherForecast);
        }

        return weatherForecasts;
    }

    public Coordinates getLatAndLonByCity(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String geocodingApiUrl = "https://api.openweathermap.org/geo/1.0/direct?q=" + city + "&appid=" + apiKey;
        JSONObject locationResponse = restTemplate.getForObject(geocodingApiUrl, JSONObject.class);

        Double lat = locationResponse.getJSONArray("lat").getDouble(0);
        Double lon = locationResponse.getJSONArray("lon").getDouble(0);

        Coordinates coordinates = new Coordinates(lat, lon);
        return coordinates;
    }*/
}


