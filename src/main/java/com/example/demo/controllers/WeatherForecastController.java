package com.example.demo.controllers;

import com.example.demo.models.responses.GetWeatherForecastResponse;
import com.example.demo.services.WeatherForecastService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherForecastController {
    @Autowired
    private WeatherForecastService weatherForecastService;

    @GetMapping("/weather/{city}")
    public ResponseEntity<GetWeatherForecastResponse> getWeatherForecast(@PathVariable String city) {
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        GetWeatherForecastResponse weatherForecast = weatherForecastService.getWeatherData(city);
        return new ResponseEntity<>(weatherForecast, HttpStatus.OK);
    }
}
