package com.carrental.service;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.dto.DamagePenaltyDto;
import com.carrental.domain.dto.RentalDto;
import com.carrental.facade.RentalFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RentalServiceTest {

    @Mock
    private RentalFacade rentalFacade;

    @InjectMocks
    private RentalService rentalService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getRentalList() {
        // Given
        List<RentalDto> expectedRentalDtoList = new ArrayList<>();
        when(rentalFacade.getRentalList()).thenReturn(expectedRentalDtoList);

        // When
        List<RentalDto> result = rentalService.getRentalList();

        // Then
        assertEquals(expectedRentalDtoList, result);
    }

    @Test
    public void getRentalById() throws RentalNotFoundException {
        // Given
        Long rentalId = 1L;
        RentalDto expectedRentalDto = new RentalDto();
        when(rentalFacade.getRentalById(rentalId)).thenReturn(expectedRentalDto);

        // When
        RentalDto result = rentalService.getRentalById(rentalId);

        // Then
        assertEquals(expectedRentalDto, result);
    }

    @Test
    public void getRentalsByCustomer() throws CustomerNotFoundException {
        // Given
        Long customerId = 1L;
        List<RentalDto> expectedRentalDtoList = new ArrayList<>();
        when(rentalFacade.getRentalsByCustomer(customerId)).thenReturn(expectedRentalDtoList);

        // When
        List<RentalDto> result = rentalService.getRentalsByCustomer(customerId);

        // Then
        assertEquals(expectedRentalDtoList, result);
    }

    @Test
    public void updateRental() throws RentalNotFoundException {
        // Given
        RentalDto rentalDto = new RentalDto();
        RentalDto expectedUpdatedRentalDto = new RentalDto();
        when(rentalFacade.updateRental(rentalDto)).thenReturn(expectedUpdatedRentalDto);

        // When
        RentalDto result = rentalService.updateRental(rentalDto);

        // Then
        assertEquals(expectedUpdatedRentalDto, result);
    }

    @Test
    public void saveRental() {
        // Given
        RentalDto rentalDto = new RentalDto();
        RentalDto expectedSavedRentalDto = new RentalDto();
        when(rentalFacade.saveRental(rentalDto)).thenReturn(expectedSavedRentalDto);

        // When
        RentalDto result = rentalService.saveRental(rentalDto);

        // Then
        assertEquals(expectedSavedRentalDto, result);
    }

    @Test
    public void addDamageToRental() throws RentalNotFoundException {
        // Given
        Long rentalId = 1L;
        DamagePenaltyDto damagePenaltyDto = new DamagePenaltyDto();
        RentalDto expectedRentalDto = new RentalDto();
        when(rentalFacade.addDamageToRental(rentalId, damagePenaltyDto)).thenReturn(expectedRentalDto);

        // When
        RentalDto result = rentalService.addDamageToRental(rentalId, damagePenaltyDto);

        // Then
        assertEquals(expectedRentalDto, result);
    }

    @Test
    public void removeDamageFromRental() throws RentalNotFoundException {
        // Given
        Long rentalId = 1L;
        RentalDto expectedRentalDto = new RentalDto();
        when(rentalFacade.removeDamageFromRental(rentalId)).thenReturn(expectedRentalDto);

        // When
        RentalDto result = rentalService.removeDamageFromRental(rentalId);

        // Then
        assertEquals(expectedRentalDto, result);
    }

    @Test
    public void deleteRentalById() throws RentalNotFoundException {
        // Given
        Long rentalId = 1L;

        // When
        assertDoesNotThrow(() -> rentalService.deleteRentalById(rentalId));

        // Then
        verify(rentalFacade, times(1)).deleteRentalById(rentalId);
    }
}