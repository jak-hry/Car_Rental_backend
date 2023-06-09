package com.carrental.service;

import com.carrental.controller.exceptions.CarNotFoundException;
import com.carrental.domain.dto.CarDto;
import com.carrental.facade.CarFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarServiceTest {

    @Mock
    private CarFacade carFacade;
    @InjectMocks
    private CarService carService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getDataBaseCarList() {
        // Given
        List<CarDto> expectedCarDtoList = new ArrayList<>();
        when(carFacade.getDataBaseCarList()).thenReturn(expectedCarDtoList);

        // When
        List<CarDto> result = carService.getDataBaseCarList();

        // Then
        assertEquals(expectedCarDtoList, result);
    }

    @Test
    public void getAPICarList() throws IOException, InterruptedException {
        // Given
        List<CarDto> expectedCarDtoList = new ArrayList<>();
        when(carFacade.getAPICarList()).thenReturn(expectedCarDtoList);

        // When
        List<CarDto> result = carService.getAPICarList();

        // Then
        assertEquals(expectedCarDtoList, result);
    }

    @Test
    public void getAvailableCars() {
        // Given
        List<CarDto> expectedCarDtoList = new ArrayList<>();
        when(carFacade.getAvailableCars()).thenReturn(expectedCarDtoList);

        // When
        List<CarDto> result = carService.getAvailableCars();

        // Then
        assertEquals(expectedCarDtoList, result);
    }

    @Test
    public void getCarById() throws CarNotFoundException {
        // Given
        Long carId = 1L;
        CarDto expectedCarDto = new CarDto(carId, "Model 1");
        when(carFacade.getCarById(carId)).thenReturn(expectedCarDto);

        // When
        CarDto result = carService.getCarById(carId);

        // Then
        assertEquals(expectedCarDto, result);
    }

    @Test
    public void getCarByModel() throws CarNotFoundException {
        // Given
        String model = "Model 1";
        CarDto expectedCarDto = new CarDto(1L, model);
        when(carFacade.getCarByModel(model)).thenReturn(expectedCarDto);

        // When
        CarDto result = carService.getCarByModel(model);

        // Then
        assertEquals(expectedCarDto, result);
    }

    @Test
    public void updateCar() throws CarNotFoundException {
        // Given
        CarDto carDto = new CarDto(1L, "Model 1");
        CarDto expectedUpdatedCarDto = new CarDto(1L, "Updated Model 1");
        when(carFacade.updateCar(carDto)).thenReturn(expectedUpdatedCarDto);

        // When
        CarDto result = carService.updateCar(carDto);

        // Then
        assertEquals(expectedUpdatedCarDto, result);
    }

    @Test
    public void saveCar() {
        // Given
        CarDto carDto = new CarDto(1L, "Model 1");
        CarDto expectedSavedCarDto = new CarDto(1L, "Model 1");
        when(carFacade.saveCar(carDto)).thenReturn(expectedSavedCarDto);

        // When
        CarDto result = carService.saveCar(carDto);

        // Then
        assertEquals(expectedSavedCarDto, result);
    }

    @Test
    public void deleteCarById() throws CarNotFoundException {
        // Given
        Long carId = 1L;

        // When
        carService.deleteCarById(carId);

        // Then
        verify(carFacade, times(1)).deleteCarById(carId);
    }
}