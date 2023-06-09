package com.carrental.service;

import com.carrental.controller.exceptions.DamagePenaltyNotFoundException;
import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.dto.DamagePenaltyDto;
import com.carrental.domain.dto.RentalDto;
import com.carrental.facade.DamagePenaltyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DamagePenaltyService {

    private final DamagePenaltyFacade damagePenaltyFacade;

    public DamagePenaltyDto createDamagePenalty(DamagePenaltyDto damagePenaltyDto, RentalDto rentalDto) throws RentalNotFoundException {
        return damagePenaltyFacade.createDamagePenalty(damagePenaltyDto, rentalDto);
    }

    public List<DamagePenaltyDto> getAllDamagePenalties() {
        return damagePenaltyFacade.getAllDamagePenalties();
    }

    public DamagePenaltyDto getDamagePenaltyById(Long id) throws DamagePenaltyNotFoundException {
        return damagePenaltyFacade.getDamagePenaltyById(id);
    }

    public void deleteDamagePenaltyById(Long id) {
        damagePenaltyFacade.deleteDamagePenaltyById(id);
    }
}