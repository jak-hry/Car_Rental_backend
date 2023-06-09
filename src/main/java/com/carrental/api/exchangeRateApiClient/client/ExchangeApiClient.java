package com.carrental.api.exchangeRateApiClient.client;

import com.carrental.api.exchangeRateApiClient.config.ExchangeConfig;
import com.carrental.domain.ExchangeRateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExchangeApiClient {

    private final ExchangeConfig config;
    private final ObjectMapper objectMapper;

    public ExchangeRateResponse fetchExchangeRateFromAPI() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getExchangeApiUrl()))
                .header("X-RapidAPI-Key", config.getExchangeApiKey())
                .header("X-RapidAPI-Host", config.getExchangeApiHost())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        if (response.statusCode() == 200) {
            return objectMapper.readValue(responseBody, ExchangeRateResponse.class);
        } else {
            throw new IOException("Failed to fetch exchange rate from API. Status code: " + response.statusCode());
        }
    }

    public ResponseEntity<Map<String, Double>> getResponse() {
        try {
            ExchangeRateResponse response = fetchExchangeRateFromAPI();
            Map<String, Double> rates = new HashMap<>();
            rates.put("USD", response.getRates().get("USD"));
            rates.put("EUR", response.getRates().get("EUR"));
            rates.put("PLN", response.getRates().get("PLN"));
            return ResponseEntity.ok(rates);
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
