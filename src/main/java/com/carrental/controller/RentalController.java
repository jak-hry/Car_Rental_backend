package com.carrental.controller;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.Customer;
import com.carrental.domain.Rental;
import com.carrental.domain.dto.RentalDto;
import com.carrental.mapper.RentalMapper;
import com.carrental.service.CustomerService;
import com.carrental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/rentals")
public class RentalController {

    private final RentalService rentalService;
    private final CustomerService customerService;
    private final RentalMapper rentalMapper;


    @GetMapping
    public ResponseEntity<List<RentalDto>> getRentals() {
        List<Rental> rentals = rentalService.getRentalList();
        return ResponseEntity.ok(rentalMapper.mapToRentalDtoList(rentals));
    }

    @GetMapping(value = "{rentalId}")
    public ResponseEntity<RentalDto> getRental(@PathVariable Long rentalId) throws RentalNotFoundException {
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(rentalService.getRentalById(rentalId)));
    }

    @GetMapping(params = "customerId")
    public ResponseEntity<List<RentalDto>> getRentalsByCustomer(@RequestParam Long customerId) throws CustomerNotFoundException {
        Customer customer = customerService.getCustomerById(customerId);
        List<Rental> rentals = rentalService.getRentalsByCustomer(customer);
        return ResponseEntity.ok(rentalMapper.mapToRentalDtoList(rentals));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        rentalService.saveRental(rental);
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(rental));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalDto> updateRental(@RequestBody RentalDto rentalDto) throws RentalNotFoundException {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        Rental savedRental = rentalService.saveRental(rental);
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(savedRental));
    }

    @DeleteMapping(value = "{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long rentalId) throws RentalNotFoundException {
        rentalService.deleteRentalById(rentalId);
        return ResponseEntity.ok().build();
    }
}