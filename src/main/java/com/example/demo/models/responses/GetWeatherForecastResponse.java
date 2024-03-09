package com.example.demo.models.responses;

import com.example.demo.models.WeatherInfo;

import java.time.LocalDateTime;
import java.util.List;

public class GetWeatherForecastResponse {
    private LocalDateTime now;
    private List<WeatherInfo> list;
    private String message;

    public GetWeatherForecastResponse() {
    }

    public GetWeatherForecastResponse(LocalDateTime now, List<WeatherInfo> list, String message) {
        this.now = now;
        this.list = list;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    public List<WeatherInfo> getList() {
        return list;
    }

    public void setList(List<WeatherInfo> list) {
        this.list = list;
    }
}
