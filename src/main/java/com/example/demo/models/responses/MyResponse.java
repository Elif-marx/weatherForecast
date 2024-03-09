package com.example.demo.models.responses;

import com.example.demo.models.WeatherList;

import java.time.LocalDateTime;
import java.util.List;

public class MyResponse {
    private LocalDateTime now;
    private List<WeatherList> list;

    public MyResponse() {
    }

    public MyResponse(LocalDateTime now, List<WeatherList> list) {
        this.now = now;
        this.list = list;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    public List<WeatherList> getList() {
        return list;
    }

    public void setList(List<WeatherList> list) {
        this.list = list;
    }
}
