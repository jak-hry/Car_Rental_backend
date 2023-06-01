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

    public Car getCarByModel(String model) throws CarNotFoundException {
        return carRepository.findByModel(model).orElseThrow(CarNotFoundException::new);
    }

    public CarDto updateCar(CarDto carDto) throws CarNotFoundException {
        Car existingCar = carRepository.findById(carDto.getId())
                .orElseThrow(CarNotFoundException::new);

        existingCar.setModel(carDto.getModel());
        existingCar.setAvailable(carDto.isAvailable());

        Car updatedCar = carRepository.save(existingCar);
        return carMapper.mapToCarDto(updatedCar);
    }

    public Car saveCar(Car car) {
        car.setAvailable(true);
        return carRepository.save(car);
    }

    public void deleteCarById(Long carId) throws CarNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        carRepository.delete(car);
    }
}