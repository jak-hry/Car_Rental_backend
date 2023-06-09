package com.carrental.mapper;

import com.carrental.domain.Car;
import com.carrental.domain.CarCategory;
import com.carrental.domain.TransmissionType;
import com.carrental.domain.dto.CarCategoryDto;
import com.carrental.domain.dto.CarDto;
import com.carrental.domain.dto.TransmissionTypeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarMapperTest {

    private CarMapper carMapper;
    private CarCategoryMapper categoryMapper;
    private TransmissionTypeMapper transmissionTypeMapper;

    @BeforeEach
    void setUp() {
        categoryMapper = mock(CarCategoryMapper.class);
        transmissionTypeMapper = mock(TransmissionTypeMapper.class);
        carMapper = new CarMapper(categoryMapper, transmissionTypeMapper);
    }

    @Test
    void shouldMapCarToCarDto1() {
        // Given
        Car car = Car.builder()
                .id(1L)
                .model("Toyota Camry")
                .available(true)
                .category(CarCategory.SEDAN)
                .transmissionType(TransmissionType.AUTOMATIC)
                .costPerDay(BigDecimal.valueOf(50.0))
                .build();

        CarCategoryDto carCategoryDto = new CarCategoryDto("SEDAN");
        TransmissionTypeDto transmissionTypeDto = new TransmissionTypeDto("AUTOMATIC");

        when(categoryMapper.mapToCarCategoryDto(car.getCategory())).thenReturn(carCategoryDto);
        when(transmissionTypeMapper.toDto(car.getTransmissionType())).thenReturn(transmissionTypeDto);

        // When
        CarDto carDto = carMapper.mapToCarDto(car);

        // Then
        assertNotNull(carDto);
        assertEquals(car.getId(), carDto.getId());
        assertEquals(car.getModel(), carDto.getModel());
        assertEquals(car.isAvailable(), carDto.isAvailable());
        assertEquals(carCategoryDto, carDto.getCategory());
        assertEquals(transmissionTypeDto, carDto.getTransmissionType());
        assertEquals(car.getCostPerDay(), carDto.getCostPerDay());
    }

    @Test
    void shouldMapCarToCarDto2() {
        // Given
        Car car = Car.builder()
                .id(2L)
                .model("Honda Civic")
                .available(true)
                .category(CarCategory.HATCHBACK)
                .transmissionType(TransmissionType.MANUAL)
                .costPerDay(BigDecimal.valueOf(40.0))
                .build();

        CarCategoryDto carCategoryDto = new CarCategoryDto("HATCHBACK");
        TransmissionTypeDto transmissionTypeDto = new TransmissionTypeDto("MANUAL");

        when(categoryMapper.mapToCarCategoryDto(car.getCategory())).thenReturn(carCategoryDto);
        when(transmissionTypeMapper.toDto(car.getTransmissionType())).thenReturn(transmissionTypeDto);

        // When
        CarDto carDto = carMapper.mapToCarDto(car);

        // Then
        assertNotNull(carDto);
        assertEquals(car.getId(), carDto.getId());
        assertEquals(car.getModel(), carDto.getModel());
        assertEquals(car.isAvailable(), carDto.isAvailable());
        assertEquals(carCategoryDto, carDto.getCategory());
        assertEquals(transmissionTypeDto, carDto.getTransmissionType());
        assertEquals(car.getCostPerDay(), carDto.getCostPerDay());
    }

    @Test
    void shouldMapCarListToCarDtoList() {
        // Given
        Car car1 = Car.builder()
                .id(1L)
                .model("Toyota Camry")
                .available(true)
                .category(CarCategory.SEDAN)
                .transmissionType(TransmissionType.AUTOMATIC)
                .costPerDay(BigDecimal.valueOf(50.0))
                .build();

        Car car2 = Car.builder()
                .id(2L)
                .model("Honda Civic")
                .available(true)
                .category(CarCategory.HATCHBACK)
                .transmissionType(TransmissionType.MANUAL)
                .costPerDay(BigDecimal.valueOf(40.0))
                .build();

        List<Car> cars = List.of(car1, car2);

        CarCategoryDto carCategoryDto1 = new CarCategoryDto("SEDAN");
        CarCategoryDto carCategoryDto2 = new CarCategoryDto("HATCHBACK");
        TransmissionTypeDto transmissionTypeDto1 = new TransmissionTypeDto("AUTOMATIC");
        TransmissionTypeDto transmissionTypeDto2 = new TransmissionTypeDto("MANUAL");

        when(categoryMapper.mapToCarCategoryDto(car1.getCategory())).thenReturn(carCategoryDto1);
        when(categoryMapper.mapToCarCategoryDto(car2.getCategory())).thenReturn(carCategoryDto2);
        when(transmissionTypeMapper.toDto(car1.getTransmissionType())).thenReturn(transmissionTypeDto1);
        when(transmissionTypeMapper.toDto(car2.getTransmissionType())).thenReturn(transmissionTypeDto2);

        // When
        List<CarDto> carDtoList = carMapper.mapToCarDtoList(cars);

        // Then
        assertNotNull(carDtoList);
        assertEquals(cars.size(), carDtoList.size());

        CarDto carDto1 = carDtoList.get(0);
        assertEquals(car1.getId(), carDto1.getId());
        assertEquals(car1.getModel(), carDto1.getModel());
        assertEquals(car1.isAvailable(), carDto1.isAvailable());
        assertEquals(carCategoryDto1, carDto1.getCategory());
        assertEquals(transmissionTypeDto1, carDto1.getTransmissionType());
        assertEquals(car1.getCostPerDay(), carDto1.getCostPerDay());

        CarDto carDto2 = carDtoList.get(1);
        assertEquals(car2.getId(), carDto2.getId());
        assertEquals(car2.getModel(), carDto2.getModel());
        assertEquals(car2.isAvailable(), carDto2.isAvailable());
        assertEquals(carCategoryDto2, carDto2.getCategory());
        assertEquals(transmissionTypeDto2, carDto2.getTransmissionType());
        assertEquals(car2.getCostPerDay(), carDto2.getCostPerDay());
    }
}