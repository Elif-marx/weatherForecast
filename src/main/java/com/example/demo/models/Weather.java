package com.example.demo.models;

public class Weather {
    private double maxTemperature;
    private double feelsLikeTemperature;
    private int humidity;

    public Weather() {
    }

    public Weather(double maxTemperature, double feelsLikeTemperature, int humidity) {
        this.maxTemperature = maxTemperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.humidity = humidity;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
