package com.carrental.controller;

import com.carrental.domain.dto.CarDto;
import com.carrental.domain.dto.TransmissionTypeDto;
import com.carrental.service.TransmissionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/transmission")
public class TransmissionTypeController {

    private final TransmissionTypeService transmissionTypeService;

    @GetMapping
    public ResponseEntity<List<TransmissionTypeDto>> getAllTransmissionTypes() {
        List<TransmissionTypeDto> transmissionTypes = transmissionTypeService.getAllTransmissionTypes();
        return ResponseEntity.ok(transmissionTypes);
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getCarsByTransmissionType(@RequestParam(required = false) Integer transmissionTypeId) {
        TransmissionTypeDto transmissionTypeDto = TransmissionTypeDto.builder().id(transmissionTypeId).build();
        List<CarDto> cars = transmissionTypeService.getCarsByTransmissionType(transmissionTypeDto);
        return ResponseEntity.ok(cars);
    }
}