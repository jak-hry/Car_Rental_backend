package com.carrental.facade;

import com.carrental.domain.Car;
import com.carrental.domain.TransmissionType;
import com.carrental.domain.dto.CarDto;
import com.carrental.domain.dto.TransmissionTypeDto;
import com.carrental.mapper.CarMapper;
import com.carrental.mapper.TransmissionTypeMapper;
import com.carrental.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransmissionTypeFacadeTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @Mock
    private TransmissionTypeMapper transmissionTypeMapper;
    private TransmissionTypeFacade transmissionTypeFacade;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transmissionTypeFacade = new TransmissionTypeFacade(carRepository, carMapper, transmissionTypeMapper);
    }

    @Test
    void shouldGetAllTransmissionTypes() {
        // Given
        List<TransmissionTypeDto> expected = Arrays.asList(
                new TransmissionTypeDto("AUTOMATIC"), new TransmissionTypeDto("MANUAL"));

        when(transmissionTypeMapper.toDto(TransmissionType.AUTOMATIC))
                .thenReturn(new TransmissionTypeDto("AUTOMATIC"));
        when(transmissionTypeMapper.toDto(TransmissionType.MANUAL))
                .thenReturn(new TransmissionTypeDto("MANUAL"));

        // When
        List<TransmissionTypeDto> result = transmissionTypeFacade.getAllTransmissionTypes();

        // Then
        assertEquals(expected.size(), result.size());
        for (int i = 0; i < expected.size(); i++) assertEquals(expected.get(i).getName(), result.get(i).getName());
    }

    @Test
    void shouldGetCarsByTransmissionType() {
        // Given
        TransmissionTypeDto transmissionTypeDto = new TransmissionTypeDto("AUTOMATIC");
        TransmissionType transmissionType = TransmissionType.AUTOMATIC;

        Car car1 = new Car();
        car1.setId(1L);
        car1.setModel("Toyota Camry");
        car1.setTransmissionType(transmissionType);

        CarDto carDto1 = new CarDto();
        carDto1.setId(1L);
        carDto1.setModel("Toyota Camry");

        List<Car> cars = Collections.singletonList(car1);
        List<CarDto> expected = Collections.singletonList(carDto1);

        when(transmissionTypeMapper.toEntity(transmissionTypeDto)).thenReturn(transmissionType);
        when(carRepository.findByTransmissionType(transmissionType)).thenReturn(cars);
        when(carMapper.mapToCarDtoList(cars)).thenReturn(expected);

        // When
        List<CarDto> result = transmissionTypeFacade.getCarsByTransmissionType(transmissionTypeDto);

        // Then
        assertEquals(expected.size(), result.size());
        assertEquals(expected.get(0).getId(), result.get(0).getId());
        assertEquals(expected.get(0).getModel(), result.get(0).getModel());
    }
}