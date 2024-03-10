package com.example.demo;

import com.example.demo.controllers.WeatherForecastController;
import com.example.demo.models.responses.GetWeatherForecastResponse;
import com.example.demo.services.WeatherForecastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(WeatherForecastController.class)
public class WeatherForecastControllerTests {
    @InjectMocks
    private WeatherForecastController weatherController;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private WeatherForecastService weatherService;

    private String city;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
        city = "TestCity";
    }
    @Test
    void getWeatherForecast_Success() throws Exception {

        LocalDateTime now = LocalDateTime.now();
        GetWeatherForecastResponse mockResponse = new GetWeatherForecastResponse(now, Collections.emptyList(), "Weather data retrieved");

        when(weatherService.getWeatherData(city)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/{city}", city)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateTime").value(now.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weatherInfoList").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Weather data retrieved"))
                .andDo(print());
    }

    @Test
    void getWeatherForecast_Exception() throws Exception {

        when(weatherService.getWeatherData(city)).thenThrow(new RuntimeException("Weather service exception"));

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/{city}", city)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Weather data could not be retrieved"));
    }
    @Test
    void getWeatherForecast_NullRequest() throws Exception {

        when(weatherService.getWeatherData(null)).thenThrow(new RuntimeException("Weather service exception"));

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/{city}", (Object[]) null)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Weather data could not be retrieved"));
    }
}
