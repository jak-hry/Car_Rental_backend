package com.carrental.carApi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CarConfig {

    @Value("${car.api.url}")
    private String carApiUrl;

    @Value("${car.api.key}")
    private String carApiKey;

    @Value("${car.api.host}")
    private String carApiHost;
}