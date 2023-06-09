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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final CarCategoryMapper categoryMapper;
    private final TransmissionTypeMapper transmissionTypeMapper;
    private final CarApiClient carApiClient;

    public List<CarDto> getDataBaseCarList() {
        List<Car> carList = carRepository.findAll();
        return carMapper.mapToCarDtoList(carList);
    }

    public List<CarDto> getAPICarList() throws IOException, InterruptedException {
        String data = carApiClient.fetchDataFromAPI();
        List<Car> carList = carApiClient.processData(data);
        return carMapper.mapToCarDtoList(carList);
    }

    public List<CarDto> getAvailableCars() {
        List<Car> availableCars = carRepository.findByAvailable(true);
        List<Car> filteredCars = new ArrayList<>();

        for (Car car : availableCars) {
            if (car.getCategory() != null && car.getTransmissionType() != null) {
                filteredCars.add(car);
            }
        }

        return carMapper.mapToCarDtoList(filteredCars);
    }

    public CarDto getCarById(Long carId) throws CarNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        return carMapper.mapToCarDto(car);
    }

    public CarDto getCarByModel(String model) throws CarNotFoundException {
        Car car = carRepository.findByModel(model).orElseThrow(CarNotFoundException::new);
        return carMapper.mapToCarDto(car);
    }

    public CarDto updateCar(CarDto carDto) throws CarNotFoundException {
        Car existingCar = carRepository.findById(carDto.getId())
                .orElseThrow(CarNotFoundException::new);

        existingCar.setModel(carDto.getModel());
        existingCar.setAvailable(carDto.isAvailable());

        CarCategoryDto carCategoryDto = carDto.getCategory();
        if (carCategoryDto != null) {
            CarCategory category = categoryMapper.mapToCarCategory(carCategoryDto);
            existingCar.setCategory(category);
        }

        TransmissionTypeDto transmissionTypeDto = carDto.getTransmissionType();
        if (transmissionTypeDto != null) {
            TransmissionType transmissionType = transmissionTypeMapper.toEntity(transmissionTypeDto);
            existingCar.setTransmissionType(transmissionType);
        }

        if (existingCar.getCategory() != null && existingCar.getTransmissionType() != null) {
            BigDecimal costPerDay = calculateCostPerDay(existingCar.getCategory(), existingCar.getTransmissionType());
            existingCar.setCostPerDay(costPerDay);
        } else {
            existingCar.setCostPerDay(null);
        }

        Car updatedCar = carRepository.save(existingCar);
        return carMapper.mapToCarDto(updatedCar);
    }

    public BigDecimal calculateCostPerDay(CarCategory category, TransmissionType transmissionType) {
        BigDecimal basePrice = BigDecimal.ZERO;

        if (category == CarCategory.SEDAN) {
            basePrice = BigDecimal.valueOf(40);
        } else if (category == CarCategory.SUV) {
            basePrice = BigDecimal.valueOf(65);
        } else if (category == CarCategory.HATCHBACK) {
            basePrice = BigDecimal.valueOf(50);
        } else if (category == CarCategory.CONVERTIBLE) {
            basePrice = BigDecimal.valueOf(80);
        } else if (category == CarCategory.SPORTS_CAR) {
            basePrice = BigDecimal.valueOf(100);
        }

        if (transmissionType == TransmissionType.MANUAL) {
            basePrice = basePrice.add(BigDecimal.valueOf(15));
        } else if (transmissionType == TransmissionType.AUTOMATIC) {
            basePrice = basePrice.add(BigDecimal.valueOf(35));
        }

        return basePrice;
    }

    public CarDto saveCar(CarDto carDto) {
        Car car = carMapper.mapToCar(carDto);
        car.setAvailable(true);
        Car savedCar = carRepository.save(car);
        return carMapper.mapToCarDto(savedCar);
    }

    public void deleteCarById(Long carId) throws CarNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        carRepository.delete(car);
    }
}