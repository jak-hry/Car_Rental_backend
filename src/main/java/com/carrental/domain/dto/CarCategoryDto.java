package com.carrental.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarCategoryDto {
    private int id;
    private String name;

    public CarCategoryDto(String name) {
        this.name = name;
    }
}