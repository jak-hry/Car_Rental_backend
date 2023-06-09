package com.carrental.service;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.dto.DamagePenaltyDto;
import com.carrental.domain.dto.RentalDto;
import com.carrental.facade.RentalFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalFacade rentalFacade;

    public List<RentalDto> getRentalList() {
        return rentalFacade.getRentalList();
    }

    public RentalDto getRentalById(Long rentalId) throws RentalNotFoundException {
        return rentalFacade.getRentalById(rentalId);
    }

    public List<RentalDto> getRentalsByCustomer(Long customerId) throws CustomerNotFoundException {
        return rentalFacade.getRentalsByCustomer(customerId);
    }

    public RentalDto updateRental(RentalDto rentalDto) throws RentalNotFoundException {
        return rentalFacade.updateRental(rentalDto);
    }

    public RentalDto saveRental(RentalDto rentalDto) {
        return rentalFacade.saveRental(rentalDto);
    }

    public RentalDto addDamageToRental(Long rentalId, DamagePenaltyDto damagePenaltyDto) throws RentalNotFoundException {
        return rentalFacade.addDamageToRental(rentalId, damagePenaltyDto);
    }

    public RentalDto removeDamageFromRental(Long rentalId) throws RentalNotFoundException {
        return rentalFacade.removeDamageFromRental(rentalId);
    }

    public void deleteRentalById(Long rentalId) throws RentalNotFoundException {
        rentalFacade.deleteRentalById(rentalId);
    }
}