package com.carrental.mapper;

import com.carrental.domain.Rental;
import com.carrental.domain.dto.RentalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RentalMapper {
    private final CarMapper carMapper;
    private final CustomerMapper customerMapper;

    public Rental mapToRental(RentalDto rentalDto) {
        return new Rental(
                rentalDto.getId(),
                carMapper.mapToCar(rentalDto.getCar()),
                customerMapper.mapToCustomer(rentalDto.getCustomer()),
                rentalDto.getStartDate(),
                rentalDto.getEndDate(),
                rentalDto.getTotalCost()
        );
    }

    public RentalDto mapToRentalDto(Rental rental) {
        return new RentalDto(
                rental.getId(),
                carMapper.mapToCarDto(rental.getCar()),
                customerMapper.mapToCustomerDto(rental.getCustomer()),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getTotalCost()
        );
    }

    public List<RentalDto> mapToRentalDtoList(List<Rental> rentals) {
        return rentals.stream()
                .map(this::mapToRentalDto)
                .collect(Collectors.toList());
    }
}