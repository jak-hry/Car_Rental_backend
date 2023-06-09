package com.carrental.controller;

import com.carrental.controller.exceptions.DamagePenaltyNotFoundException;
import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.dto.DamagePenaltyDto;
import com.carrental.domain.dto.RentalDto;
import com.carrental.service.DamagePenaltyService;
import com.carrental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/damage-penalties")
@RequiredArgsConstructor
public class DamagePenaltyController {
    private final DamagePenaltyService damagePenaltyService;
    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<DamagePenaltyDto> createDamagePenalty(@RequestBody DamagePenaltyDto damagePenaltyDto) throws RentalNotFoundException {
        RentalDto rentalDto = rentalService.getRentalById(damagePenaltyDto.getRental().getId());
        DamagePenaltyDto createdDamagePenalty = damagePenaltyService.createDamagePenalty(damagePenaltyDto, rentalDto);
        return ResponseEntity.ok(createdDamagePenalty);
    }

    @GetMapping
    public ResponseEntity<List<DamagePenaltyDto>> getAllDamagePenalties() {
        List<DamagePenaltyDto> damagePenalties = damagePenaltyService.getAllDamagePenalties();
        return ResponseEntity.ok(damagePenalties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DamagePenaltyDto> getDamagePenaltyById(@PathVariable Long id) throws DamagePenaltyNotFoundException {
        DamagePenaltyDto damagePenalty = damagePenaltyService.getDamagePenaltyById(id);
        return ResponseEntity.ok(damagePenalty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDamagePenaltyById(@PathVariable Long id) {
        damagePenaltyService.deleteDamagePenaltyById(id);
        return ResponseEntity.ok().build();
    }
}