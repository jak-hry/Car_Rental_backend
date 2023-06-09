package com.carrental.service;

import com.carrental.controller.exceptions.CarNotFoundException;
import com.carrental.domain.CarCategory;
import com.carrental.domain.TransmissionType;
import com.carrental.domain.dto.CarDto;
import com.carrental.facade.CarFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarFacade carFacade;

    public List<CarDto> getDataBaseCarList() {
        return carFacade.getDataBaseCarList();
    }

    public List<CarDto> getAPICarList() throws IOException, InterruptedException {
        return carFacade.getAPICarList();
    }

    public List<CarDto> getAvailableCars() {
        return carFacade.getAvailableCars();
    }

    public CarDto getCarById(Long carId) throws CarNotFoundException {
        return carFacade.getCarById(carId);
    }

    public CarDto getCarByModel(String model) throws CarNotFoundException {
        return carFacade.getCarByModel(model);
    }

    public CarDto updateCar(CarDto carDto) throws CarNotFoundException {
        return carFacade.updateCar(carDto);
    }

    public CarDto saveCar(CarDto carDto) {
        return carFacade.saveCar(carDto);
    }

    public void deleteCarById(Long carId) throws CarNotFoundException {
        carFacade.deleteCarById(carId);
    }
}