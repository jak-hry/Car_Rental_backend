package com.carrental.service;

import com.carrental.domain.Car;
import com.carrental.domain.CarCategory;
import com.carrental.domain.dto.CarCategoryDto;
import com.carrental.domain.dto.CarDto;
import com.carrental.mapper.CarMapper;
import com.carrental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarCategoryService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarDto> getCarsByCategory(CarCategory category) {
        List<Car> cars = carRepository.findByCategory(category);
        return carMapper.mapToCarDtoList(cars);
    }

    public List<CarCategoryDto> getAllCategories() {
        return Arrays.stream(CarCategory.values())
                .map(category -> new CarCategoryDto(category.ordinal(), category.name()))
                .collect(Collectors.toList());
    }
}