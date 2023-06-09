package com.carrental.service;

import com.carrental.controller.exceptions.DamagePenaltyNotFoundException;
import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.dto.DamagePenaltyDto;
import com.carrental.domain.dto.RentalDto;
import com.carrental.facade.DamagePenaltyFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DamagePenaltyServiceTest {

    @Mock
    private DamagePenaltyFacade damagePenaltyFacade;

    @InjectMocks
    private DamagePenaltyService damagePenaltyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createDamagePenalty() throws RentalNotFoundException {
        // Given
        DamagePenaltyDto damagePenaltyDto = new DamagePenaltyDto();
        RentalDto rentalDto = new RentalDto();
        DamagePenaltyDto expectedDamagePenaltyDto = new DamagePenaltyDto();
        when(damagePenaltyFacade.createDamagePenalty(damagePenaltyDto, rentalDto)).thenReturn(expectedDamagePenaltyDto);

        // When
        DamagePenaltyDto result = damagePenaltyService.createDamagePenalty(damagePenaltyDto, rentalDto);

        // Then
        assertEquals(expectedDamagePenaltyDto, result);
    }

    @Test
    public void getAllDamagePenalties() {
        // Given
        List<DamagePenaltyDto> expectedDamagePenaltyDtoList = new ArrayList<>();
        when(damagePenaltyFacade.getAllDamagePenalties()).thenReturn(expectedDamagePenaltyDtoList);

        // When
        List<DamagePenaltyDto> result = damagePenaltyService.getAllDamagePenalties();

        // Then
        assertEquals(expectedDamagePenaltyDtoList, result);
    }

    @Test
    public void getDamagePenaltyById() throws DamagePenaltyNotFoundException {
        // Given
        Long id = 1L;
        DamagePenaltyDto expectedDamagePenaltyDto = new DamagePenaltyDto();
        when(damagePenaltyFacade.getDamagePenaltyById(id)).thenReturn(expectedDamagePenaltyDto);

        // When
        DamagePenaltyDto result = damagePenaltyService.getDamagePenaltyById(id);

        // Then
        assertEquals(expectedDamagePenaltyDto, result);
    }

    @Test
    public void deleteDamagePenaltyById() {
        // Given
        Long id = 1L;

        // When
        assertDoesNotThrow(() -> damagePenaltyService.deleteDamagePenaltyById(id));

        // Then
        verify(damagePenaltyFacade, times(1)).deleteDamagePenaltyById(id);
    }
}