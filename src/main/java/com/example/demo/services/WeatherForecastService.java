package com.example.demo.services;

import com.example.demo.models.WeatherInfo;
import com.example.demo.models.WeatherList;
import com.example.demo.models.responses.GetWeatherForecastResponse;
import com.example.demo.models.responses.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class WeatherForecastService {
    @Value("${openweathermap.api.key}")
    private String API_KEY;
    @Autowired
    RestTemplate restTemplate;
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private LocalDateTime now = LocalDateTime.now();

    public GetWeatherForecastResponse getWeatherData(String city) {
        //String apiUrl = BASE_URL + "forecast?q=" + city + "&appid=" + API_KEY;

        String apiUrl = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .path("/forecast")
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .build()
                .toUriString();

        try {
            OpenWeatherResponse openWeatherResponse = restTemplate.getForObject(apiUrl, OpenWeatherResponse.class);

            List<WeatherInfo> weatherInfoList = get48hoursForecast(openWeatherResponse.getList());

            return new GetWeatherForecastResponse(now, weatherInfoList, "Weather data retrieved.");
        } catch (Exception e) {
            e.printStackTrace();
            return handleException();
        }
    }

    private GetWeatherForecastResponse handleException() {
        GetWeatherForecastResponse errorResponse = new GetWeatherForecastResponse();
        errorResponse.setMessage("Weather data could not be retrieved.");
        return errorResponse;
    }

    private List<WeatherInfo> get48hoursForecast(List<WeatherList> weatherLists) {
        LocalDateTime endDateTime = now.plusHours(48);
        List<WeatherList> twoDaysList = weatherLists.stream().
                filter(weather -> weather.getDateTime().isAfter(now) &&
                        weather.getDateTime().isBefore(endDateTime)).collect(Collectors.toList());

        return getWeatherInfoFrom48hoursForecast(twoDaysList);
    }

    private List<WeatherInfo> getWeatherInfoFrom48hoursForecast(List<WeatherList> twoDaysList) {
        List<WeatherInfo> weatherInfoList = new ArrayList<>();
        for (WeatherList weatherList : twoDaysList) {
            WeatherInfo info = new WeatherInfo();
            info.setDateTime(weatherList.getDt_txt());
            info.setFeels_like(weatherList.getMain().getFeels_like());
            info.setHumidity(weatherList.getMain().getHumidity());
            info.setMax_temp(weatherList.getMain().getTemp_max());
            weatherInfoList.add(info);
        }
        return weatherInfoList;
    }

}


