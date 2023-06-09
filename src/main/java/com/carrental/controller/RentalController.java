package com.carrental.controller;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.dto.RentalDto;
import com.carrental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/rentals")
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public ResponseEntity<List<RentalDto>> getRentals() {
        List<RentalDto> rentalDto = rentalService.getRentalList();
        return ResponseEntity.ok(rentalDto);
    }

    @GetMapping(value = "{rentalId}")
    public ResponseEntity<RentalDto> getRental(@PathVariable Long rentalId) throws RentalNotFoundException {
        RentalDto rentalDto = rentalService.getRentalById(rentalId);
        return ResponseEntity.ok(rentalDto);
    }

    @GetMapping(params = "customerId")
    public ResponseEntity<List<RentalDto>> getRentalsByCustomer(@RequestParam Long customerId) throws CustomerNotFoundException {
        List<RentalDto> rentalDto = rentalService.getRentalsByCustomer(customerId);
        return ResponseEntity.ok(rentalDto);
    }

    @PostMapping
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDto) {
        RentalDto createdRentalDto = rentalService.saveRental(rentalDto);
        return ResponseEntity.ok(createdRentalDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> updateRental(@PathVariable Long id, @RequestBody RentalDto rentalDto) throws RentalNotFoundException {
        rentalDto.setId(id);
        RentalDto updatedRentalDto = rentalService.updateRental(rentalDto);
        return ResponseEntity.ok(updatedRentalDto);
    }

    @DeleteMapping(value = "{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long rentalId) throws RentalNotFoundException {
        rentalService.deleteRentalById(rentalId);
        return ResponseEntity.ok().build();
    }
}