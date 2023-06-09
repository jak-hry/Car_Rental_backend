package com.carrental.api.exchangeRateApiClient.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ExchangeConfig {

    @Value("${exchange.api.url}")
    private String exchangeApiUrl;

    @Value("${exchange.api.key}")
    private String exchangeApiKey;

    @Value("${exchange.api.host}")
    private String exchangeApiHost;
}