package com.carrental.controller;

import com.carrental.controller.exceptions.CarNotFoundException;
import com.carrental.domain.Car;
import com.carrental.domain.dto.CarDto;
import com.carrental.mapper.CarMapper;
import com.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/cars")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping
    public ResponseEntity<List<CarDto>> getCars() {
        List<Car> cars = carService.getCarList();
        return ResponseEntity.ok(carMapper.mapToCarDtoList(cars));
    }

    @GetMapping(value = "{carId}")
    public ResponseEntity<CarDto> getCar(@PathVariable Long carId) throws CarNotFoundException {
        return ResponseEntity.ok(carMapper.mapToCarDto(carService.getCarById(carId)));
    }

    @GetMapping(params = "brand")
    public ResponseEntity<List<CarDto>> getCarsByBrand(@RequestParam String brand) {
        List<Car> cars = carService.getCarsByBrand(brand);
        return ResponseEntity.ok(carMapper.mapToCarDtoList(cars));
    }

    @GetMapping(params = "model")
    public ResponseEntity<List<CarDto>> getCarsByModel(@RequestParam String model) {
        List<Car> cars = carService.getCarsByModel(model);
        return ResponseEntity.ok(carMapper.mapToCarDtoList(cars));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        Car car = carMapper.mapToCar(carDto);
        carService.saveCar(car);
        return ResponseEntity.ok(carMapper.mapToCarDto(car));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto) throws CarNotFoundException {
        Car car = carMapper.mapToCar(carDto);
        Car savedCar = carService.saveCar(car);
        return ResponseEntity.ok(carMapper.mapToCarDto(savedCar));
    }

    @DeleteMapping(value = "{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId) throws CarNotFoundException {
        carService.deleteCarById(carId);
        return ResponseEntity.ok().build();
    }
}
