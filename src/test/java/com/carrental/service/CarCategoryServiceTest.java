package com.carrental.service;

import com.carrental.domain.CarCategory;
import com.carrental.domain.dto.CarCategoryDto;
import com.carrental.domain.dto.CarDto;
import com.carrental.facade.CarCategoryFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CarCategoryServiceTest {

    @Mock
    private CarCategoryFacade categoryFacade;

    @InjectMocks
    private CarCategoryService carCategoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCarsByCategory() {
        // Given
        CarCategory category = CarCategory.SPORTS_CAR;
        List<CarDto> expectedCarDtoList = new ArrayList<>();
        when(categoryFacade.getCarsByCategory(category)).thenReturn(expectedCarDtoList);

        // When
        List<CarDto> result = carCategoryService.getCarsByCategory(category);

        // Then
        assertEquals(expectedCarDtoList, result);
    }

    @Test
    public void getAllCategories() {
        // Given
        List<CarCategoryDto> expectedCategoryDtoList = new ArrayList<>();
        when(categoryFacade.getAllCategories()).thenReturn(expectedCategoryDtoList);

        // When
        List<CarCategoryDto> result = carCategoryService.getAllCategories();

        // Then
        assertEquals(expectedCategoryDtoList, result);
    }
}