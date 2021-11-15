package web.controller;

import com.google.gson.Gson;
import data.model.StandardResponse;
import data.model.StatusResponse;
import data.model.WeatherAndCityDetails;
import lombok.extern.slf4j.Slf4j;
import service.WeatherService;
import service.WeatherServiceImpl;
import service.config.ObjectLoader;
import service.exceptions.WeatherInfoSystemException;
import static spark.Spark.*;

@Slf4j
public class WeatherAppController {
    private static final WeatherService weatherService = new WeatherServiceImpl();
    public static void main(String[] args) {
        path("/api/v1", ()->{
            before("/*", (q, a) -> log.info("Received api call"));
            get("/city/:city", (request, response)-> {
                response.type("application/json");
                String cityName = request.params(":city");
                try {
                    Long woeid = weatherService.getWoeidForCity(cityName);
                    response.redirect("consolidatedWeatherForCity/"+woeid);
                }
                catch (WeatherInfoSystemException exception){
                    log.info("Exception --> {}", exception.getMessage());
                    return (new StandardResponse(StatusResponse.ERROR, exception.getMessage()));
                }
                return new StandardResponse(StatusResponse.SUCCESS, "Call Successful");
            });

            get("/consolidatedWeatherForCity/:woeid", (request, response) ->{
                response.type("application/json");
                Long woeid = Long.valueOf(request.params(":woeid"));
                try{
                    WeatherAndCityDetails weatherAndCityDetails = weatherService.getConsolidatedWeatherForCity(woeid);
                    return (new StandardResponse(StatusResponse.SUCCESS, ObjectLoader.getGson().toJsonTree(weatherAndCityDetails)));
                }
                catch (WeatherInfoSystemException exception){
                    log.info("Exception --> {}", exception.getMessage());
                    return (new StandardResponse(StatusResponse.ERROR, exception.getMessage()));
                }
            });
        });
    }
}
