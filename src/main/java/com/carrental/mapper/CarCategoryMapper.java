package com.carrental.mapper;

import com.carrental.domain.CarCategory;
import com.carrental.domain.dto.CarCategoryDto;
import org.springframework.stereotype.Service;

@Service
public class CarCategoryMapper {

    public CarCategoryDto mapToCarCategoryDto(CarCategory category) {
        if (category == null) {
            return null;
        }

        return CarCategoryDto.builder()
                .name(category.getName())
                .build();
    }

    public CarCategory mapToCarCategory(CarCategoryDto categoryDto) {
        if (categoryDto == null || categoryDto.getName() == null) {
            return null;
        }

        try {
            return CarCategory.valueOf(categoryDto.getName());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
