package com.carrental.mapper;

import com.carrental.domain.Car;
import com.carrental.domain.Customer;
import com.carrental.domain.Rental;
import com.carrental.domain.dto.CarDto;
import com.carrental.domain.dto.CustomerDto;
import com.carrental.domain.dto.RentalDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class RentalMapperTest {

    @Mock
    private CarMapper carMapper;
    @Mock
    private CustomerMapper customerMapper;

    private RentalMapper rentalMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rentalMapper = new RentalMapper(carMapper, customerMapper);
    }

    @Test
    void shouldMapRentalDtoToRental() {
        // Given
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(1L);
        rentalDto.setCar(new CarDto());
        rentalDto.setCustomer(new CustomerDto());
        rentalDto.setStartDate(LocalDateTime.now());
        rentalDto.setEndDate(LocalDateTime.now().plusDays(7));
        rentalDto.setTotalCost(BigDecimal.valueOf(100.0));

        Car car = new Car();
        Customer customer = new Customer();

        when(carMapper.mapToCar(rentalDto.getCar())).thenReturn(car);
        when(customerMapper.mapToCustomer(rentalDto.getCustomer())).thenReturn(customer);

        // When
        Rental rental = rentalMapper.mapToRental(rentalDto);

        // Then
        assertNotNull(rental);
        assertEquals(rentalDto.getId(), rental.getId());
        assertEquals(car, rental.getCar());
        assertEquals(customer, rental.getCustomer());
        assertEquals(rentalDto.getStartDate(), rental.getStartDate());
        assertEquals(rentalDto.getEndDate(), rental.getEndDate());
        assertEquals(rentalDto.getTotalCost(), rental.getTotalCost());
    }

    @Test
    void shouldMapRentalToRentalDto() {
        // Given
        Rental rental = new Rental();
        rental.setId(1L);
        rental.setCar(new Car());
        rental.setCustomer(new Customer());
        rental.setStartDate(LocalDateTime.now());
        rental.setEndDate(LocalDateTime.now().plusDays(7));
        rental.setTotalCost(BigDecimal.valueOf(100.0));

        CarDto carDto = new CarDto();
        CustomerDto customerDto = new CustomerDto();

        when(carMapper.mapToCarDto(rental.getCar())).thenReturn(carDto);
        when(customerMapper.mapToCustomerDto(rental.getCustomer())).thenReturn(customerDto);

        // When
        RentalDto rentalDto = rentalMapper.mapToRentalDto(rental);

        // Then
        assertNotNull(rentalDto);
        assertEquals(rental.getId(), rentalDto.getId());
        assertEquals(carDto, rentalDto.getCar());
        assertEquals(customerDto, rentalDto.getCustomer());
        assertEquals(rental.getStartDate(), rentalDto.getStartDate());
        assertEquals(rental.getEndDate(), rentalDto.getEndDate());
        assertEquals(rental.getTotalCost(), rentalDto.getTotalCost());
    }

    @Test
    void shouldMapRentalListToRentalDtoList() {
        // Given
        Rental rental1 = new Rental();
        rental1.setId(1L);
        rental1.setCar(new Car());
        rental1.setCustomer(new Customer());
        rental1.setStartDate(LocalDateTime.now());
        rental1.setEndDate(LocalDateTime.now().plusDays(7));
        rental1.setTotalCost(BigDecimal.valueOf(100.0));

        Rental rental2 = new Rental();
        rental2.setId(2L);
        rental2.setCar(new Car());
        rental2.setCustomer(new Customer());
        rental2.setStartDate(LocalDateTime.now());
        rental2.setEndDate(LocalDateTime.now().plusDays(14));
        rental2.setTotalCost(BigDecimal.valueOf(200.0));

        List<Rental> rentals = List.of(rental1, rental2);

        CarDto carDto1 = new CarDto();
        CarDto carDto2 = new CarDto();
        CustomerDto customerDto1 = new CustomerDto();
        CustomerDto customerDto2 = new CustomerDto();

        when(carMapper.mapToCarDto(rental1.getCar())).thenReturn(carDto1);
        when(carMapper.mapToCarDto(rental2.getCar())).thenReturn(carDto2);
        when(customerMapper.mapToCustomerDto(rental1.getCustomer())).thenReturn(customerDto1);
        when(customerMapper.mapToCustomerDto(rental2.getCustomer())).thenReturn(customerDto2);

        // When
        List<RentalDto> rentalDtoList = rentalMapper.mapToRentalDtoList(rentals);

        // Then
        assertNotNull(rentalDtoList);
        assertEquals(rentals.size(), rentalDtoList.size());

        for (int i = 0; i < rentals.size(); i++) {
            Rental rental = rentals.get(i);
            RentalDto rentalDto = rentalDtoList.get(i);

            assertEquals(rental.getId(), rentalDto.getId());
            assertEquals(carMapper.mapToCarDto(rental.getCar()), rentalDto.getCar());
            assertEquals(customerMapper.mapToCustomerDto(rental.getCustomer()), rentalDto.getCustomer());
            assertEquals(rental.getStartDate(), rentalDto.getStartDate());
            assertEquals(rental.getEndDate(), rentalDto.getEndDate());
            assertEquals(rental.getTotalCost(), rentalDto.getTotalCost());
        }
    }
}