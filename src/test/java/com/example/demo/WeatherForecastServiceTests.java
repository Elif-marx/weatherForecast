package com.example.demo;

import com.example.demo.models.Main;
import com.example.demo.models.WeatherList;
import com.example.demo.models.responses.GetWeatherForecastResponse;
import com.example.demo.models.responses.OpenWeatherResponse;
import com.example.demo.services.WeatherForecastService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


public class WeatherForecastServiceTests {
    @InjectMocks
    private WeatherForecastService weatherForecastService;
    @Mock
    private RestTemplate restTemplate;
    String city = "London";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(weatherForecastService, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(weatherForecastService, "API_KEY", "2809289ff6eb5b8c6ea19a8fedf036d8");
    }
    @Test
    void getWeatherData_Success() {
        String city = "TestCity";
        LocalDateTime now = LocalDateTime.now();
        OpenWeatherResponse mockResponse = createMockResponse();

        when(restTemplate.getForObject(anyString(), eq(OpenWeatherResponse.class))).thenReturn(mockResponse);

        GetWeatherForecastResponse result = weatherForecastService.getWeatherData(city);

        assertEquals(3, result.getList().size());
        assertEquals("Weather data retrieved.", result.getMessage());
    }

    @Test
    void getWeatherData_Exception() {
        String city = "TestCity";

        when(restTemplate.getForObject(anyString(), eq(OpenWeatherResponse.class))).thenThrow(new RuntimeException("Simulated exception"));

        GetWeatherForecastResponse result = weatherForecastService.getWeatherData(city);

        assertEquals("Weather data could not be retrieved.", result.getMessage());
    }

    private OpenWeatherResponse createMockResponse() {
        List<WeatherList> mockWeatherLists = Arrays.asList(
                createMockWeatherList("2024-03-09 12:00:00", 25.0, 60, 30.0),
                createMockWeatherList("2024-03-09 15:00:00", 27.0, 55, 32.0),
                createMockWeatherList("2024-03-09 18:00:00", 23.0, 70, 28.0)
        );
        return new OpenWeatherResponse(mockWeatherLists);
    }

    private WeatherList createMockWeatherList(String dateTime, double feelsLike, int humidity, double maxTemp) {
        WeatherList weatherList = new WeatherList();
        weatherList.setDt_txt(dateTime);
        Main main = new Main();
        main.setFeels_like(feelsLike);
        main.setHumidity(humidity);
        main.setTemp_max(maxTemp);
        weatherList.setMain(main);
        return weatherList;
    }

    @Test
    void getWeatherData_Exception1() {
        String city = "TestCity";

        when(restTemplate.getForObject(anyString(), OpenWeatherResponse.class))
                .thenThrow(new RuntimeException("API call failed"));

        GetWeatherForecastResponse expectedResponse = new GetWeatherForecastResponse();
        expectedResponse.setMessage("Weather data could not be retrieved.");

        GetWeatherForecastResponse actualResponse = weatherForecastService.getWeatherData(city);

        assertEquals(expectedResponse, actualResponse);
    }
}
