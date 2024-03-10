package com.example.demo;

import com.example.demo.controllers.WeatherForecastController;
import com.example.demo.models.responses.GetWeatherForecastResponse;
import com.example.demo.services.WeatherForecastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@ExtendWith(MockitoExtension.class)
public class WeatherForecastControllerTests {
    @InjectMocks
    private WeatherForecastController weatherController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WeatherForecastService weatherService;

    private String city;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
        city = "TestCity";
        now = LocalDateTime.now();
    }

    @Test
    void getWeatherForecast_Success() throws Exception {

        GetWeatherForecastResponse mockResponse = new GetWeatherForecastResponse(now, Collections.emptyList(), "Weather data retrieved");

        when(weatherService.getWeatherData(city)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/{city}", city)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.list").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Weather data retrieved"))
                .andDo(print());
    }

    @Test
    void getWeatherForecast_Exception() throws Exception {
        GetWeatherForecastResponse mockResponse = new GetWeatherForecastResponse(now, Collections.emptyList(), "Weather data could not be retrieved");
        when(weatherService.getWeatherData(city)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/{city}", city)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Weather data could not be retrieved"));
    }

}
