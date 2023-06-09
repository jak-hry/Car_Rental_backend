package com.carrental.service;

import com.carrental.api.carApi.client.CarApiClient;
import com.carrental.controller.exceptions.CarNotFoundException;
import com.carrental.domain.Car;
import com.carrental.domain.CarCategory;
import com.carrental.domain.TransmissionType;
import com.carrental.domain.dto.CarCategoryDto;
import com.carrental.domain.dto.CarDto;
import com.carrental.domain.dto.TransmissionTypeDto;
import com.carrental.mapper.CarCategoryMapper;
import com.carrental.mapper.CarMapper;
import com.carrental.mapper.TransmissionTypeMapper;
import com.carrental.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @Mock
    private CarCategoryMapper categoryMapper;

    @Mock
    private TransmissionTypeMapper transmissionTypeMapper;

    @Mock
    private CarApiClient carApiClient;
    @Mock
    private CarService carService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carService = new CarService(carRepository, carMapper, categoryMapper, transmissionTypeMapper, carApiClient);
    }

    @Test
    public void getDataBaseCarList() {
        // Given
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1L, "Model 1"));
        carList.add(new Car(2L, "Model 2"));

        List<CarDto> carDtoList = new ArrayList<>();
        carDtoList.add(new CarDto(1L, "Model 1"));
        carDtoList.add(new CarDto(2L, "Model 2"));
        when(carRepository.findAll()).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        // When
        List<CarDto> result = carService.getDataBaseCarList();

        // Then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void getAPICarList() throws Exception {
        // Given
        String data = "[{\"id\": 1, \"name\": \"Car 1\"}, {\"id\": 2, \"name\": \"Car 2\"}]";
        when(carApiClient.fetchDataFromAPI()).thenReturn(data);

        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1L, "Car 1"));
        carList.add(new Car(2L, "Car 2"));

        List<CarDto> carDtoList = new ArrayList<>();
        carDtoList.add(new CarDto(1L, "Model 1"));
        carDtoList.add(new CarDto(2L, "Model 2"));
        when(carApiClient.processData(data)).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        // When
        List<CarDto> result = carService.getAPICarList();

        // Then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void getAvailableCars() {
        // Given
        List<Car> availableCars = new ArrayList<>();
        availableCars.add(new Car(1L, "Model 1", true));
        availableCars.add(new Car(2L, "Model 2", true));
        when(carRepository.findByAvailable(true)).thenReturn(availableCars);

        CarDto carDto1 = new CarDto(1L, "Model 1");
        CarDto carDto2 = new CarDto(2L, "Model 2");
        List<CarDto> filteredCars = new ArrayList<>();
        filteredCars.add(carDto1);
        filteredCars.add(carDto2);
        when(carMapper.mapToCarDtoList(anyList())).thenReturn(filteredCars);

        // When
        List<CarDto> result = carService.getAvailableCars();

        // Then
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(carDto1, result.get(0));
        Assertions.assertEquals(carDto2, result.get(1));
    }

    @Test
    public void getCarById() throws CarNotFoundException {
        // Given
        Long carId = 1L;
        Car car = new Car(carId, "Model 1");
        CarDto carDto = new CarDto(carId, "Model 1");
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);

        // When
        CarDto result = carService.getCarById(carId);

        // Then
        Assertions.assertEquals(carId, result.getId());
    }

    @Test
    public void getCarByModel() throws CarNotFoundException {
        // Given
        String model = "Model 1";
        Car car = new Car(1L, model);
        CarDto carDto = new CarDto(1L, model);
        when(carRepository.findByModel(model)).thenReturn(Optional.of(car));
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);

        // When
        CarDto result = carService.getCarByModel(model);

        // Then
        Assertions.assertEquals(model, result.getModel());
    }

    @Test
    public void saveCar() {
        // Given
        CarDto carDto = new CarDto(1L, "Model 1", true);
        Car car = new Car(1L, "Model 1", true);
        when(carMapper.mapToCar(carDto)).thenReturn(car);

        Car savedCar = new Car(1L, "Model 1", true);
        when(carRepository.save(car)).thenReturn(savedCar);
        when(carMapper.mapToCarDto(savedCar)).thenReturn(carDto);

        // When
        CarDto result = carService.saveCar(carDto);

        // Then
        Assertions.assertEquals(carDto.getModel(), result.getModel());
        Assertions.assertTrue(result.isAvailable());
    }

    @Test
    public void deleteCarById() throws CarNotFoundException {
        // Given
        Long carId = 1L;
        Car car = new Car(carId, "Model 1");
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        // When
        carService.deleteCarById(carId);

        // Then
        verify(carRepository, times(1)).delete(car);
    }
}