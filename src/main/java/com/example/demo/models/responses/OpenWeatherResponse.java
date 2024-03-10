package com.example.demo.models.responses;

import com.example.demo.models.*;

import java.util.List;

public class OpenWeatherResponse {
    private String cod;
    private Integer message;
    private Integer cnt;
    private List<WeatherList> list;
    private City city;

    public OpenWeatherResponse(String cod, Integer message, Integer cnt, List<WeatherList> list, City city) {
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }
    public OpenWeatherResponse(List<WeatherList> list) {
        this.list=list;
    }
    public OpenWeatherResponse() {
    }

    public String getCod() {
        return cod;
    }
    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<WeatherList> getList() {
        return list;
    }

    public void setList(List<WeatherList> list) {
        this.list = list;
    }
}
