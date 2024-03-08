package com.example.demo;

import com.example.demo.services.WeatherForecastService;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

@WebMvcTest(WeatherForecastService.class)
@SpringBootTest
public class WeatherForecastServiceTests {
    @InjectMocks
    private WeatherForecastService weatherService;


}
