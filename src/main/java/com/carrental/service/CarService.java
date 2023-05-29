package com.carrental.service;

import com.carrental.controller.exceptions.CarNotFoundException;
import com.carrental.domain.Car;
import com.carrental.domain.dto.CarDto;
import com.carrental.mapper.CarMapper;
import com.carrental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<Car> getCarList() {
        return carRepository.findAll();
    }

    public Car getCarById(Long carId) throws CarNotFoundException {
        return carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
    }

    public List<Car> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    public List<Car> getCarsByModel(String model) {
        return carRepository.findByModel(model);
    }

    public CarDto updateCar(CarDto carDto) throws CarNotFoundException {
        Car existingCar = carRepository.findById(carDto.getId())
                .orElseThrow(CarNotFoundException::new);

        existingCar.setBrand(carDto.getBrand());
        existingCar.setModel(carDto.getModel());
        existingCar.setYear(carDto.getYear());
        existingCar.setRegistrationNumber(carDto.getRegistrationNumber());
        existingCar.setColor(carDto.getColor());
        existingCar.setRentalPricePerDay(carDto.getRentalPricePerDay());
        existingCar.setAvailable(carDto.isAvailable());

        Car updatedCar = carRepository.save(existingCar);
        return carMapper.mapToCarDto(updatedCar);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCarById(Long carId) throws CarNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        carRepository.delete(car);
    }
}