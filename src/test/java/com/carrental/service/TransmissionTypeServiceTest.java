package com.carrental.service;

import com.carrental.domain.dto.CarDto;
import com.carrental.domain.dto.TransmissionTypeDto;
import com.carrental.facade.TransmissionTypeFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TransmissionTypeServiceTest {

    @Mock
    private TransmissionTypeFacade transmissionTypeFacade;

    @InjectMocks
    private TransmissionTypeService transmissionTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTransmissionTypes() {
        // Given
        List<TransmissionTypeDto> expectedTransmissionTypeDtoList = new ArrayList<>();
        when(transmissionTypeFacade.getAllTransmissionTypes()).thenReturn(expectedTransmissionTypeDtoList);

        // When
        List<TransmissionTypeDto> result = transmissionTypeService.getAllTransmissionTypes();

        // Then
        assertEquals(expectedTransmissionTypeDtoList, result);
    }

    @Test
    public void getCarsByTransmissionType() {
        // Given
        TransmissionTypeDto transmissionTypeDto = new TransmissionTypeDto();
        List<CarDto> expectedCarDtoList = new ArrayList<>();
        when(transmissionTypeFacade.getCarsByTransmissionType(transmissionTypeDto)).thenReturn(expectedCarDtoList);

        // When
        List<CarDto> result = transmissionTypeService.getCarsByTransmissionType(transmissionTypeDto);

        // Then
        assertEquals(expectedCarDtoList, result);
    }
}