package com.carrental.controller;

import com.carrental.controller.exceptions.CarNotFoundException;
import com.carrental.domain.dto.CarDto;
import com.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> getDataBaseCars() {
        return ResponseEntity.ok(carService.getDataBaseCarList());
    }

    @GetMapping("/api")
    public ResponseEntity<List<CarDto>> getAPICars() throws IOException, InterruptedException {
        return ResponseEntity.ok(carService.getAPICarList());
    }

    @GetMapping("/available")
    public ResponseEntity<List<CarDto>> getAvailableCars() {
        return ResponseEntity.ok(carService.getAvailableCars());
    }

    @GetMapping(value = "{carId}")
    public ResponseEntity<CarDto> getCar(@PathVariable Long carId) throws CarNotFoundException {
        return ResponseEntity.ok(carService.getCarById(carId));
    }

    @GetMapping(params = "model")
    public ResponseEntity<CarDto> getCarByModel(@RequestParam String model) throws CarNotFoundException {
        return ResponseEntity.ok(carService.getCarByModel(model));
    }

    @PostMapping
    public ResponseEntity<CarDto> saveCar(@RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.saveCar(carDto));
    }

    @PutMapping(value = "{carId}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long carId, @RequestBody CarDto carDto) throws CarNotFoundException {
        carDto.setId(carId);
        CarDto updatedCar = carService.updateCar(carDto);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping(value = "{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId) throws CarNotFoundException {
        carService.deleteCarById(carId);
        return ResponseEntity.ok().build();
    }
}