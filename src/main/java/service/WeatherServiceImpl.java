package service;

import data.model.LocationDetails;
import data.model.WeatherAndCityDetails;
import lombok.extern.slf4j.Slf4j;
import service.config.ObjectLoader;
import service.exceptions.WeatherInfoSystemException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class WeatherServiceImpl implements WeatherService{
    @Override
    public Long getWoeidForCity(String cityName) throws WeatherInfoSystemException {
        HttpRequest requestCallToWeatherApi = null;
        try {
            requestCallToWeatherApi = HttpRequest.newBuilder()
                    .uri(new URI("https://www.metaweather.com/api/location/search/?query="+cityName))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new WeatherInfoSystemException("Invalid URI Syntax");
        }
        HttpResponse<InputStream> httpResponse = null;
        try {
            httpResponse = HttpClient.newHttpClient().send(requestCallToWeatherApi,
                    HttpResponse.BodyHandlers.ofInputStream());
        } catch (IOException | InterruptedException e) {
            throw new WeatherInfoSystemException(e.getMessage());
        }

        LocationDetails[] locationDetails = ObjectLoader.load(httpResponse.body(), LocationDetails[].class);
        if (locationDetails.length != 0){
            LocationDetails cityLocationDetails = locationDetails[0];
            return cityLocationDetails.getWoeid();
        }
        else{
            throw new WeatherInfoSystemException("Invalid city details");
        }
    }

    @Override
    public WeatherAndCityDetails getConsolidatedWeatherForCity(Long woeid) throws WeatherInfoSystemException {
        HttpRequest requestCallToWeatherApi = null;
        try {
            requestCallToWeatherApi = HttpRequest.newBuilder()
                    .uri(new URI("https://www.metaweather.com/api/location/"+woeid+"/"))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new WeatherInfoSystemException("Invalid URI Syntax");
        }
        HttpResponse<InputStream> httpResponse = null;
        try {
            httpResponse = HttpClient.newHttpClient().send(requestCallToWeatherApi,
                    HttpResponse.BodyHandlers.ofInputStream());
        } catch (IOException | InterruptedException e) {
            throw new WeatherInfoSystemException(e.getMessage());
        }

        WeatherAndCityDetails weatherAndCityDetails = ObjectLoader.load(httpResponse.body(), WeatherAndCityDetails.class);
        if (weatherAndCityDetails != null){
            log.info("weather and city details --> {}", weatherAndCityDetails);
            return weatherAndCityDetails;
        }
        else{
            throw new WeatherInfoSystemException("Invalid city details");
        }
    }
}
