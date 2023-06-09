package com.carrental.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {
    private Long id;
    private String model;
    private boolean available;
    private CarCategoryDto category;
    private TransmissionTypeDto transmissionType;
    private BigDecimal costPerDay;
    private List<RentalDto> rentals;
    private boolean damaged;

    public CarDto(Long id, String model, boolean available) {
        this.id = id;
        this.model = model;
        this.available = available;
    }

    public CarDto(Long id, String model) {
        this.id = id;
        this.model = model;
    }

    public CarDto(Long id, String model, CarCategoryDto category) {
        this.id = id;
        this.model = model;
        this.category = category;
    }
}