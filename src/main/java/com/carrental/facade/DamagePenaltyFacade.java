package com.carrental.facade;

import com.carrental.controller.exceptions.DamagePenaltyNotFoundException;
import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.Car;
import com.carrental.domain.DamagePenalty;
import com.carrental.domain.Rental;
import com.carrental.domain.dto.DamagePenaltyDto;
import com.carrental.domain.dto.RentalDto;
import com.carrental.mapper.DamagePenaltyMapper;
import com.carrental.repository.DamagePenaltyRepository;
import com.carrental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DamagePenaltyFacade {
    private final DamagePenaltyRepository damagePenaltyRepository;
    private final DamagePenaltyMapper damagePenaltyMapper;
    private final RentalRepository rentalRepository;

    public DamagePenaltyDto createDamagePenalty(DamagePenaltyDto damagePenaltyDto, RentalDto rentalDto) throws RentalNotFoundException {
        DamagePenalty damagePenalty = damagePenaltyMapper.mapToDamagePenalty(damagePenaltyDto);
        Rental rental = rentalRepository.findById(rentalDto.getId())
                .orElseThrow(RentalNotFoundException::new);
        damagePenalty.setRental(rental);
        Car car = rental.getCar();
        car.setDamaged(true);
        DamagePenalty savedDamagePenalty = damagePenaltyRepository.save(damagePenalty);
        return damagePenaltyMapper.mapToDamagePenaltyDto(savedDamagePenalty);
    }

    public List<DamagePenaltyDto> getAllDamagePenalties() {
        List<DamagePenalty> damagePenalties = damagePenaltyRepository.findAll();
        return damagePenaltyMapper.mapToDamagePenaltyDtoList(damagePenalties);
    }

    public DamagePenaltyDto getDamagePenaltyById(Long id) throws DamagePenaltyNotFoundException {
        DamagePenalty damagePenalty = damagePenaltyRepository.findById(id)
                .orElseThrow(DamagePenaltyNotFoundException::new);
        return damagePenaltyMapper.mapToDamagePenaltyDto(damagePenalty);
    }

    public void deleteDamagePenaltyById(Long id) {
        damagePenaltyRepository.deleteById(id);
    }
}
