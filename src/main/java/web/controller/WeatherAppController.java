package web.controller;

import data.model.LocationDetails;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import service.ObjectLoader;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static spark.Spark.*;

@Slf4j
public class WeatherAppController {
    public static void main(String[] args) {
        path("/api/v1", ()->{
            before("/*", (q, a) -> log.info("Received api call"));
            get("/city/:city", (request, response)-> {
                response.type("application/json");
                String cityName = request.params(":city");
                HttpRequest requestCallToWeatherApi = HttpRequest.newBuilder()
                        .uri(new URI("https://www.metaweather.com/api/location/search/?query="+cityName))
                        .GET()
                        .build();
                HttpResponse<InputStream> httpResponse = HttpClient.newHttpClient().send(requestCallToWeatherApi,
                        HttpResponse.BodyHandlers.ofInputStream());

                LocationDetails[] locationDetails = ObjectLoader.load(httpResponse.body(), LocationDetails[].class);
                return locationDetails[0];
            });
        });
    }
}
