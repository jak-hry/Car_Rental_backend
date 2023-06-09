package com.carrental.service;

import com.carrental.domain.dto.CarDto;
import com.carrental.domain.dto.TransmissionTypeDto;
import com.carrental.facade.TransmissionTypeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransmissionTypeService {

    private final TransmissionTypeFacade transmissionTypeFacade;

    public List<TransmissionTypeDto> getAllTransmissionTypes() {
        return transmissionTypeFacade.getAllTransmissionTypes();
    }

    public List<CarDto> getCarsByTransmissionType(TransmissionTypeDto transmissionTypeDto) {
        return transmissionTypeFacade.getCarsByTransmissionType(transmissionTypeDto);
    }
}