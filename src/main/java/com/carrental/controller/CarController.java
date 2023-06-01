package com.carrental.controller;

import com.carrental.carApi.client.CarApiClient;
import com.carrental.controller.exceptions.CarNotFoundException;
import com.carrental.domain.Car;
import com.carrental.domain.dto.CarDto;
import com.carrental.mapper.CarMapper;
import com.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final CarApiClient carApiClient;

    @GetMapping
    public ResponseEntity<List<CarDto>> getCars() {
        try {
            String data = carApiClient.fetchDataFromAPI();
            List<Car> cars = carApiClient.processData(data);
            return ResponseEntity.ok(carMapper.mapToCarDtoList(cars));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "{carId}")
    public ResponseEntity<CarDto> getCar(@PathVariable Long carId) throws CarNotFoundException {
        return ResponseEntity.ok(carMapper.mapToCarDto(carService.getCarById(carId)));
    }

    @GetMapping(params = "model")
    public ResponseEntity<CarDto> getCarByModel(@RequestParam String model) throws CarNotFoundException {
        return ResponseEntity.ok(carMapper.mapToCarDto(carService.getCarByModel(model)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        try {
            Car car = carMapper.mapToCar(carDto);
            carService.saveCar(car);
            return ResponseEntity.ok(carMapper.mapToCarDto(car));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long id, @RequestBody CarDto carDto) throws CarNotFoundException {
        carDto.setId(id);
        CarDto updatedCar = carService.updateCar(carDto);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping(value = "{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId) throws CarNotFoundException {
        carService.deleteCarById(carId);
        return ResponseEntity.ok().build();
    }
}
