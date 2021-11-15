package service;

import data.model.LocationDetails;
import data.model.WeatherAndCityDetails;
import service.exceptions.WeatherInfoSystemException;

import java.io.IOException;

public interface WeatherService {
    Long getWoeidForCity(String city) throws IOException, InterruptedException, WeatherInfoSystemException;
    WeatherAndCityDetails getConsolidatedWeatherForCity(Long woeid) throws WeatherInfoSystemException;
}
