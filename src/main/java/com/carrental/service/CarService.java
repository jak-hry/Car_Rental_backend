package com.carrental.service;

import com.carrental.controller.exceptions.CarNotFoundException;
import com.carrental.domain.Car;
import com.carrental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

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

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCarById(Long carId) throws CarNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        carRepository.delete(car);
    }
}