package com.carrental.service;

import com.carrental.domain.CarCategory;
import com.carrental.domain.dto.CarCategoryDto;
import com.carrental.domain.dto.CarDto;
import com.carrental.facade.CarCategoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarCategoryService {

    private final CarCategoryFacade categoryFacade;

    public List<CarDto> getCarsByCategory(CarCategory category) {
        return categoryFacade.getCarsByCategory(category);
    }

    public List<CarCategoryDto> getAllCategories() {
        return categoryFacade.getAllCategories();
    }
}