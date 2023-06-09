package com.carrental.mapper;

import com.carrental.domain.CarCategory;
import com.carrental.domain.dto.CarCategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarCategoryMapperTest {

    private CarCategoryMapper carCategoryMapper;

    @BeforeEach
    void setUp() {
        carCategoryMapper = new CarCategoryMapper();
    }

    @Test
    void shouldMapCarCategoryToCarCategoryDto() {
        // Given
        CarCategory category = CarCategory.SEDAN;

        // When
        CarCategoryDto categoryDto = carCategoryMapper.mapToCarCategoryDto(category);

        // Then
        assertNotNull(categoryDto);
        assertEquals(category.getName().toUpperCase(), categoryDto.getName().toUpperCase());
    }

    @Test
    void shouldReturnNullWhenMappingNullCarCategoryToCarCategoryDto() {
        // When
        CarCategoryDto categoryDto = carCategoryMapper.mapToCarCategoryDto(null);

        // Then
        assertNull(categoryDto);
    }

    @Test
    void shouldMapCarCategoryDtoToCarCategory() {
        // Given
        CarCategoryDto categoryDto = new CarCategoryDto();
        categoryDto.setName("SEDAN");

        // When
        CarCategory category = carCategoryMapper.mapToCarCategory(categoryDto);

        // Then
        assertNotNull(category);
        assertTrue(categoryDto.getName().equalsIgnoreCase(category.getName()));
    }

    @Test
    void shouldReturnNullWhenMappingNullCarCategoryDtoToCarCategory() {
        // When
        CarCategory category = carCategoryMapper.mapToCarCategory(null);

        // Then
        assertNull(category);
    }

    @Test
    void shouldReturnNullWhenMappingCarCategoryDtoWithNullNameToCarCategory() {
        // Given
        CarCategoryDto categoryDto = new CarCategoryDto();

        // When
        CarCategory category = carCategoryMapper.mapToCarCategory(categoryDto);

        // Then
        assertNull(category);
    }

    @Test
    void shouldReturnNullWhenMappingInvalidCarCategoryDtoToCarCategory() {
        // Given
        CarCategoryDto categoryDto = new CarCategoryDto();
        categoryDto.setName("INVALID_CATEGORY");

        // When
        CarCategory category = carCategoryMapper.mapToCarCategory(categoryDto);

        // Then
        assertNull(category);
    }
}