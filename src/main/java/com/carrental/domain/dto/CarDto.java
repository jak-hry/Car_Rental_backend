package com.carrental.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String registrationNumber;
    private String color;
    private BigDecimal rentalPricePerDay;
    private boolean available;
}