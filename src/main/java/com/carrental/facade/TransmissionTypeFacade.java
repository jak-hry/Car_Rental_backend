package com.carrental.facade;

import com.carrental.domain.Car;
import com.carrental.domain.TransmissionType;
import com.carrental.domain.dto.CarDto;
import com.carrental.domain.dto.TransmissionTypeDto;
import com.carrental.mapper.CarMapper;
import com.carrental.mapper.TransmissionTypeMapper;
import com.carrental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransmissionTypeFacade {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final TransmissionTypeMapper transmissionTypeMapper;

    public List<TransmissionTypeDto> getAllTransmissionTypes() {
        List<TransmissionTypeDto> transmissionTypes = new ArrayList<>();
        for (TransmissionType transmissionType : TransmissionType.values()) {
            transmissionTypes.add(transmissionTypeMapper.toDto(transmissionType));
        }
        return transmissionTypes;
    }

    public List<CarDto> getCarsByTransmissionType(TransmissionTypeDto transmissionTypeDto) {
        TransmissionType transmissionType = transmissionTypeMapper.toEntity(transmissionTypeDto);
        if (transmissionType != null) {
            List<Car> cars = carRepository.findByTransmissionType(transmissionType);
            return carMapper.mapToCarDtoList(cars);
        }
        return Collections.emptyList();
    }
}
