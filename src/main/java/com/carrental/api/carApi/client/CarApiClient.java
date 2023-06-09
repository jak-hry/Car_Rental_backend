package com.carrental.api.carApi.client;

import com.carrental.api.carApi.config.CarConfig;
import com.carrental.domain.Car;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CarApiClient {

    private final CarConfig config;

    public String fetchDataFromAPI() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getCarApiUrl()))
                .header("X-RapidAPI-Key", config.getCarApiKey())
                .header("X-RapidAPI-Host", config.getCarApiHost())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public List<Car> processData(String data) {
        List<Car> cars = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonCar = jsonArray.getJSONObject(i);
            Long id = jsonCar.getLong("id");
            String name = jsonCar.getString("name");
            Car car = new Car(id, name);
            cars.add(car);
        }
        return cars;
    }
}