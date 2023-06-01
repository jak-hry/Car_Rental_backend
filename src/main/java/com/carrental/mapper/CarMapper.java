package com.carrental.mapper;

import com.carrental.domain.Car;
import com.carrental.domain.dto.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMapper {

    public Car mapToCar(CarDto carDto) {
        return Car.builder()
                .id(carDto.getId())
                .model(carDto.getModel())
                .available(carDto.isAvailable())
                .build();
    }

    public CarDto mapToCarDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .model(car.getModel())
                .available(car.isAvailable())
                .build();
    }

    public List<CarDto> mapToCarDtoList(List<Car> cars) {
        return cars.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }
}