package com.carrental.service;

import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.Customer;
import com.carrental.domain.Rental;
import com.carrental.domain.dto.RentalDto;
import com.carrental.mapper.RentalMapper;
import com.carrental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public List<Rental> getRentalList() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Long rentalId) throws RentalNotFoundException {
        return rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
    }

    public List<Rental> getRentalsByCustomer(Customer customer) {
        return rentalRepository.findByCustomer(customer);
    }

    public RentalDto updateRental(RentalDto rentalDto) throws RentalNotFoundException {
        Rental existingRental = rentalRepository.findById(rentalDto.getId())
                .orElseThrow(RentalNotFoundException::new);

        existingRental.setStartDate(rentalDto.getStartDate());
        existingRental.setEndDate(rentalDto.getEndDate());
        existingRental.setTotalCost(rentalDto.getTotalCost());

        Rental updatedRental = rentalRepository.save(existingRental);
        return rentalMapper.mapToRentalDto(updatedRental);
    }

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public void deleteRentalById(Long rentalId) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        rentalRepository.delete(rental);
    }
}
