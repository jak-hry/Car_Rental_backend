package com.carrental.controller;

import com.carrental.domain.CarCategory;
import com.carrental.domain.dto.CarCategoryDto;
import com.carrental.domain.dto.CarDto;
import com.carrental.service.CarCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/category")
public class CarCategoryController {

    private final CarCategoryService carCategoryService;

    @GetMapping(params = "category")
    public ResponseEntity<List<CarDto>> getCarsByCategory(@RequestParam CarCategory category) {
        return ResponseEntity.ok(carCategoryService.getCarsByCategory(category));
    }

    @GetMapping
    public ResponseEntity<List<CarCategoryDto>> getAllCategories() {
        return ResponseEntity.ok(carCategoryService.getAllCategories());
    }
}