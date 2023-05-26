package com.carrental.mapper;

import com.carrental.domain.Car;
import com.carrental.domain.dto.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMapper {

    public Car mapToCar(CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getYear(),
                carDto.getRegistrationNumber(),
                carDto.getColor(),
                carDto.getRentalPricePerDay(),
                carDto.isAvailable()
        );
    }

    public CarDto mapToCarDto(Car car) {
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getRegistrationNumber(),
                car.getColor(),
                car.getRentalPricePerDay(),
                car.isAvailable()
        );
    }

    public List<CarDto> mapToCarDtoList(List<Car> cars) {
        return cars.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }
}