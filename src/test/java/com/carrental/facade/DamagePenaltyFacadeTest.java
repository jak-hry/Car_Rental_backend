package com.carrental.facade;

import com.carrental.controller.exceptions.DamagePenaltyNotFoundException;
import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.Car;
import com.carrental.domain.DamagePenalty;
import com.carrental.domain.Rental;
import com.carrental.domain.dto.DamagePenaltyDto;
import com.carrental.domain.dto.RentalDto;
import com.carrental.mapper.DamagePenaltyMapper;
import com.carrental.repository.DamagePenaltyRepository;
import com.carrental.repository.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DamagePenaltyFacadeTest {

    @Mock
    private DamagePenaltyRepository damagePenaltyRepository;

    @Mock
    private DamagePenaltyMapper damagePenaltyMapper;

    @Mock
    private RentalRepository rentalRepository;

    private DamagePenaltyFacade damagePenaltyFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        damagePenaltyFacade = new DamagePenaltyFacade(damagePenaltyRepository, damagePenaltyMapper, rentalRepository);
    }

    @Test
    void testCreateDamagePenalty() throws RentalNotFoundException {
        Long rentalId = 1L;
        Long damagePenaltyId = 1L;

        DamagePenaltyDto damagePenaltyDto = new DamagePenaltyDto();
        damagePenaltyDto.setId(damagePenaltyId);

        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(rentalId);

        DamagePenalty damagePenalty = new DamagePenalty();
        damagePenalty.setId(damagePenaltyId);

        Rental rental = new Rental();
        rental.setId(rentalId);
        Car car = new Car();
        rental.setCar(car);

        when(rentalRepository.findById(rentalDto.getId())).thenReturn(Optional.of(rental));
        when(damagePenaltyMapper.mapToDamagePenalty(damagePenaltyDto)).thenReturn(damagePenalty);
        when(damagePenaltyMapper.mapToDamagePenaltyDto(any(DamagePenalty.class))).thenReturn(damagePenaltyDto);
        when(damagePenaltyRepository.save(damagePenalty)).thenReturn(damagePenalty);

        DamagePenaltyDto createdDamagePenalty = damagePenaltyFacade.createDamagePenalty(damagePenaltyDto, rentalDto);

        assertEquals(damagePenaltyId, createdDamagePenalty.getId());
        assertTrue(car.isDamaged());
    }

    @Test
    void testGetAllDamagePenalties() {
        Long damagePenaltyId1 = 1L;
        Long damagePenaltyId2 = 2L;

        DamagePenalty damagePenalty1 = new DamagePenalty();
        damagePenalty1.setId(damagePenaltyId1);

        DamagePenalty damagePenalty2 = new DamagePenalty();
        damagePenalty2.setId(damagePenaltyId2);

        List<DamagePenalty> damagePenalties = Arrays.asList(damagePenalty1, damagePenalty2);

        when(damagePenaltyRepository.findAll()).thenReturn(damagePenalties);

        List<DamagePenaltyDto> damagePenaltyDto = new ArrayList<>();
        DamagePenaltyDto damagePenaltyDto1 = new DamagePenaltyDto();
        damagePenaltyDto1.setId(damagePenaltyId1);
        DamagePenaltyDto damagePenaltyDto2 = new DamagePenaltyDto();
        damagePenaltyDto2.setId(damagePenaltyId2);
        damagePenaltyDto.add(damagePenaltyDto1);
        damagePenaltyDto.add(damagePenaltyDto2);
        when(damagePenaltyMapper.mapToDamagePenaltyDtoList(damagePenalties)).thenReturn(damagePenaltyDto);

        List<DamagePenaltyDto> result = damagePenaltyFacade.getAllDamagePenalties();

        assertEquals(2, result.size());
        assertEquals(damagePenaltyId1, result.get(0).getId());
        assertEquals(damagePenaltyId2, result.get(1).getId());
    }

    @Test
    void testGetDamagePenaltyById() throws DamagePenaltyNotFoundException {
        Long damagePenaltyId = 1L;

        DamagePenalty damagePenalty = new DamagePenalty();
        damagePenalty.setId(damagePenaltyId);
        DamagePenaltyDto damagePenaltyDto = new DamagePenaltyDto(1L, new RentalDto(), "desc", BigDecimal.ONE);

        when(damagePenaltyRepository.findById(damagePenaltyId)).thenReturn(Optional.of(damagePenalty));
        when(damagePenaltyMapper.mapToDamagePenaltyDto(damagePenalty)).thenReturn(damagePenaltyDto);

        DamagePenaltyDto result = damagePenaltyFacade.getDamagePenaltyById(damagePenaltyId);

        assertEquals(damagePenaltyId, result.getId());
    }

    @Test
    void testDeleteDamagePenaltyById() {
        Long damagePenaltyId = 1L;

        damagePenaltyFacade.deleteDamagePenaltyById(damagePenaltyId);

        verify(damagePenaltyRepository, times(1)).deleteById(damagePenaltyId);
    }
}