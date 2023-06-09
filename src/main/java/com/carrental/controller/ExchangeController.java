package com.carrental.controller;

import com.carrental.api.exchangeRateApiClient.client.ExchangeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/exchange")
public class ExchangeController {

    private final ExchangeApiClient exchangeApiClient;

    @GetMapping
    public ResponseEntity<Map<String, Double>> getExchangeRate() {
        return exchangeApiClient.getResponse();
    }
}