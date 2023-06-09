package com.carrental.facade;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.*;
import com.carrental.domain.dto.CustomerDto;
import com.carrental.domain.dto.DamagePenaltyDto;
import com.carrental.domain.dto.RentalDto;
import com.carrental.mapper.CustomerMapper;
import com.carrental.mapper.DamagePenaltyMapper;
import com.carrental.mapper.RentalMapper;
import com.carrental.repository.CarRepository;
import com.carrental.repository.DamagePenaltyRepository;
import com.carrental.repository.RentalRepository;
import com.carrental.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RentalFacadeTest {

    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private DamagePenaltyRepository damagePenaltyRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private RentalMapper rentalMapper;
    @Mock
    private CustomerService customerService;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private DamagePenaltyMapper damagePenaltyMapper;

    private RentalFacade rentalFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rentalFacade = new RentalFacade(rentalRepository,
                damagePenaltyRepository,
                carRepository,
                rentalMapper,
                customerService,
                customerMapper,
                damagePenaltyMapper);
    }

    @Test
    void updateRentalShouldUpdateRental() throws RentalNotFoundException {
        // Given
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(1L);
        rentalDto.setStartDate(LocalDateTime.parse("2023-01-01T00:00:00"));
        rentalDto.setEndDate(LocalDateTime.parse("2023-01-05T00:00:00"));
        rentalDto.setTotalCost(BigDecimal.valueOf(100.0));

        Rental existingRental = new Rental();

        when(rentalRepository.findById(rentalDto.getId())).thenReturn(Optional.of(existingRental));
        when(rentalMapper.mapToRentalDto(existingRental)).thenReturn(rentalDto);
        when(rentalRepository.save(existingRental)).thenReturn(existingRental);

        // When
        RentalDto result = rentalFacade.updateRental(rentalDto);

        // Then
        assertNotNull(result);
        assertSame(rentalDto, result);
        assertEquals(rentalDto.getStartDate(), existingRental.getStartDate());
        assertEquals(rentalDto.getEndDate(), existingRental.getEndDate());
        assertEquals(rentalDto.getTotalCost(), existingRental.getTotalCost());
        verify(rentalRepository, times(1)).findById(rentalDto.getId());
        verify(rentalMapper, times(1)).mapToRentalDto(existingRental);
        verify(rentalRepository, times(1)).save(existingRental);
    }

    @Test
    void getRentalListShouldReturnListOfRentalDto() {
        // Given
        Rental rental1 = new Rental();
        rental1.setId(1L);
        Rental rental2 = new Rental();
        rental2.setId(2L);
        List<Rental> rentalList = Arrays.asList(rental1, rental2);

        RentalDto rentalDto1 = new RentalDto();
        rentalDto1.setId(1L);
        RentalDto rentalDto2 = new RentalDto();
        rentalDto2.setId(2L);
        List<RentalDto> expected = Arrays.asList(rentalDto1, rentalDto2);

        when(rentalRepository.findAll()).thenReturn(rentalList);
        when(rentalMapper.mapToRentalDtoList(rentalList)).thenReturn(expected);

        // When
        List<RentalDto> result = rentalFacade.getRentalList();

        // Then
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
        assertSame(expected.get(0), result.get(0));
        assertSame(expected.get(1), result.get(1));
        verify(rentalRepository, times(1)).findAll();
        verify(rentalMapper, times(1)).mapToRentalDtoList(rentalList);
    }

    @Test
    void getRentalsByCustomerShouldReturnListOfRentalDto() throws CustomerNotFoundException {
        // Given
        Long customerId = 1L;

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerId);

        Rental rental1 = new Rental();
        rental1.setId(1L);
        Rental rental2 = new Rental();
        rental2.setId(2L);
        List<Rental> rentals = Arrays.asList(rental1, rental2);

        RentalDto rentalDto1 = new RentalDto();
        rentalDto1.setId(1L);
        RentalDto rentalDto2 = new RentalDto();
        rentalDto2.setId(2L);
        List<RentalDto> expected = Arrays.asList(rentalDto1, rentalDto2);

        when(customerService.getCustomerById(customerId)).thenReturn(customerDto);
        when(rentalRepository.findByCustomer(customerMapper.mapToCustomer(customerDto))).thenReturn(rentals);
        when(rentalMapper.mapToRentalDtoList(rentals)).thenReturn(expected);

        // When
        List<RentalDto> result = rentalFacade.getRentalsByCustomer(customerId);

        // Then
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
        assertSame(expected.get(0), result.get(0));
        assertSame(expected.get(1), result.get(1));
        verify(customerService, times(1)).getCustomerById(customerId);
        verify(rentalRepository, times(1)).findByCustomer(customerMapper.mapToCustomer(customerDto));
        verify(rentalMapper, times(1)).mapToRentalDtoList(rentals);
    }

    @Test
    void saveRentalShouldReturnSavedRentalDto() {
        // Given
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(1L);
        rentalDto.setStartDate(LocalDateTime.parse("2023-01-01T00:00:00"));
        rentalDto.setEndDate(LocalDateTime.parse("2023-01-05T00:00:00"));
        rentalDto.setTotalCost(BigDecimal.valueOf(100.0));
        rentalDto.setRentalDuration(3);

        Car car = new Car(1L, "Model", true, CarCategory.SEDAN, TransmissionType.AUTOMATIC, BigDecimal.TEN);

        Rental rental = new Rental();
        rental.setId(1L);
        rental.setCar(car);

        when(rentalMapper.mapToRental(rentalDto)).thenReturn(rental);
        when(carRepository.save(any(Car.class))).thenReturn(car);
        when(rentalRepository.save(rental)).thenReturn(rental);
        when(rentalMapper.mapToRentalDto(rental)).thenReturn(rentalDto);

        // When
        RentalDto result = rentalFacade.saveRental(rentalDto);

        // Then
        assertNotNull(result);
        assertSame(rentalDto, result);
        assertFalse(car.isAvailable());
        verify(rentalMapper, times(1)).mapToRental(rentalDto);
        verify(carRepository, times(1)).save(any(Car.class));
        verify(rentalRepository, times(1)).save(rental);
        verify(rentalMapper, times(1)).mapToRentalDto(rental);
    }

    @Test
    void shouldRemoveDamageFromRental() throws RentalNotFoundException {
        // Given
        Long rentalId = 1L;
        Rental rental = new Rental();
        rental.setId(rentalId);
        DamagePenalty damagePenalty = new DamagePenalty();
        Car car = new Car(1L, "Model 1", true, CarCategory.SPORTS_CAR, TransmissionType.MANUAL, BigDecimal.TEN, false);
        rental.setDamagePenalty(damagePenalty);
        rental.setCar(car);

        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);
        when(rentalMapper.mapToRentalDto(rental)).thenReturn(new RentalDto());

        // When
        RentalDto result = rentalFacade.removeDamageFromRental(rentalId);

        // Then
        assertNotNull(result);
        assertNull(rental.getDamagePenalty());
        assertFalse(car.isDamaged());
        verify(rentalRepository, times(1)).save(rental);
        verify(damagePenaltyRepository, times(1)).delete(damagePenalty);
    }

    @Test
    void shouldAddDamageToRental() throws RentalNotFoundException {
        // Given
        Long rentalId = 1L;
        Rental rental = new Rental();
        rental.setId(rentalId);
        DamagePenaltyDto damagePenaltyDto = new DamagePenaltyDto();
        DamagePenalty damagePenalty = new DamagePenalty();
        Car car = new Car(1L, "Model 1", true, CarCategory.SPORTS_CAR, TransmissionType.MANUAL, BigDecimal.TEN, false);
        rental.setCar(car);

        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);
        when(damagePenaltyMapper.mapToDamagePenalty(damagePenaltyDto)).thenReturn(damagePenalty);
        when(rentalMapper.mapToRentalDto(rental)).thenReturn(new RentalDto());

        // When
        RentalDto result = rentalFacade.addDamageToRental(rentalId, damagePenaltyDto);

        // Then
        assertNotNull(result);
        assertSame(damagePenalty, rental.getDamagePenalty());
        assertTrue(car.isDamaged());
        verify(rentalRepository, times(1)).save(rental);
    }
}