package com.example.demo.models;

import java.time.LocalDateTime;

public class WeatherInfo {
    private String dateTime;
    private Double max_temp;
    private Double feels_like;
    private Integer humidity;

    public WeatherInfo(String dateTime, Double max_temp, Double feels_like, Integer humidity) {
        this.dateTime = dateTime;
        this.max_temp = max_temp;
        this.feels_like = feels_like;
        this.humidity = humidity;
    }

    public WeatherInfo() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(Double max_temp) {
        this.max_temp = max_temp;
    }

    public Double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(Double feels_like) {
        this.feels_like = feels_like;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}
