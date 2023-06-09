package com.carrental.mapper;

import com.carrental.domain.Car;
import com.carrental.domain.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarMapper {

    private final CarCategoryMapper categoryMapper;
    private final TransmissionTypeMapper transmissionTypeMapper;

    public Car mapToCar(CarDto carDto) {
        return Car.builder()
                .id(carDto.getId())
                .model(carDto.getModel())
                .available(carDto.isAvailable())
                .category(categoryMapper.mapToCarCategory(carDto.getCategory()))
                .transmissionType(transmissionTypeMapper.toEntity(carDto.getTransmissionType()))
                .costPerDay(carDto.getCostPerDay())
                .damaged(carDto.isDamaged())
                .build();
    }

    public CarDto mapToCarDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .model(car.getModel())
                .available(car.isAvailable())
                .category(categoryMapper.mapToCarCategoryDto(car.getCategory()))
                .transmissionType(transmissionTypeMapper.toDto(car.getTransmissionType()))
                .costPerDay(car.getCostPerDay())
                .damaged(car.isDamaged())
                .build();
    }

    public List<CarDto> mapToCarDtoList(List<Car> cars) {
        return cars.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }
}