package com.example.demo.controllers;

import com.example.demo.models.Weather;
import com.example.demo.services.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherForecastController {
    @Autowired
    private WeatherForecastService weatherService;

    @GetMapping("/weather/{city}")
    public ResponseEntity<String> getWeatherForecast(@PathVariable String city) {
        String weatherForecast = weatherService.getWeatherData(city);
        return new ResponseEntity<>(weatherForecast, HttpStatus.OK);    }
}
