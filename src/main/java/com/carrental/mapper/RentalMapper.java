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
        return Rental.builder()
                .id(rentalDto.getId())
                .car(carMapper.mapToCar(rentalDto.getCar()))
                .customer(customerMapper.mapToCustomer(rentalDto.getCustomer()))
                .startDate(rentalDto.getStartDate())
                .endDate(rentalDto.getEndDate())
                .totalCost(rentalDto.getTotalCost())
                .rentalDuration(rentalDto.getRentalDuration())
                .build();
    }

    public RentalDto mapToRentalDto(Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .car(carMapper.mapToCarDto(rental.getCar()))
                .customer(customerMapper.mapToCustomerDto(rental.getCustomer()))
                .startDate(rental.getStartDate())
                .endDate(rental.getEndDate())
                .totalCost(rental.getTotalCost())
                .rentalDuration(rental.getRentalDuration())
                .build();
    }

    public List<RentalDto> mapToRentalDtoList(List<Rental> rentals) {
        return rentals.stream()
                .map(this::mapToRentalDto)
                .collect(Collectors.toList());
    }
}