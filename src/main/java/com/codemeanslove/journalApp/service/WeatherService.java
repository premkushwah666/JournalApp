package com.codemeanslove.journalApp.service;

import com.codemeanslove.journalApp.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private static final String API = "164730812faa51e22332ec2792a57c81";
    private static final String apiKey = "https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String url = apiKey.replace("{city name}",city).replace("{API key}",API);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET,null,WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
