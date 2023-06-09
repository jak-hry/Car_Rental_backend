package com.carrental.facade;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.controller.exceptions.RentalNotFoundException;
import com.carrental.domain.Car;
import com.carrental.domain.DamagePenalty;
import com.carrental.domain.Rental;
import com.carrental.domain.dto.CustomerDto;
import com.carrental.domain.dto.DamagePenaltyDto;
import com.carrental.domain.dto.RentalDto;
import com.carrental.mapper.CustomerMapper;
import com.carrental.mapper.DamagePenaltyMapper;
import com.carrental.mapper.RentalMapper;
import com.carrental.repository.CarRepository;
import com.carrental.repository.DamagePenaltyRepository;
import com.carrental.repository.RentalRepository;
import com.carrental.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalFacade {
    private final RentalRepository rentalRepository;
    private final DamagePenaltyRepository damagePenaltyRepository;
    private final CarRepository carRepository;
    private final RentalMapper rentalMapper;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final DamagePenaltyMapper damagePenaltyMapper;

    public List<RentalDto> getRentalList() {
        List<Rental> rentalList = rentalRepository.findAll();
        return rentalMapper.mapToRentalDtoList(rentalList);
    }

    public RentalDto getRentalById(Long rentalId) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        return rentalMapper.mapToRentalDto(rental);
    }

    public List<RentalDto> getRentalsByCustomer(Long customerId) throws CustomerNotFoundException {
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        List<Rental> rentals = rentalRepository.findByCustomer(customerMapper.mapToCustomer(customerDto));
        return rentalMapper.mapToRentalDtoList(rentals);
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

    public RentalDto saveRental(RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        Car car = rental.getCar();
        car.setAvailable(false);

        Integer rentalDuration = rentalDto.getRentalDuration();
        BigDecimal costPerDay = car.getCostPerDay();
        BigDecimal totalCost = costPerDay.multiply(BigDecimal.valueOf(rentalDuration));
        rental.setTotalCost(totalCost);
        rental.setRentalDuration(rentalDuration);

        carRepository.save(car);
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.mapToRentalDto(savedRental);
    }

    public RentalDto addDamageToRental(Long rentalId, DamagePenaltyDto damagePenaltyDto) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        DamagePenalty damagePenalty = damagePenaltyMapper.mapToDamagePenalty(damagePenaltyDto);
        damagePenalty.setRental(rental);
        rental.setDamagePenalty(damagePenalty);
        Car car = rental.getCar();
        car.setDamaged(true);
        Rental updatedRental = rentalRepository.save(rental);
        return rentalMapper.mapToRentalDto(updatedRental);
    }

    public RentalDto removeDamageFromRental(Long rentalId) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        DamagePenalty damagePenalty = rental.getDamagePenalty();
        rental.setDamagePenalty(null);
        Car car = rental.getCar();
        car.setDamaged(false);
        rentalRepository.save(rental);
        damagePenaltyRepository.delete(damagePenalty);
        return rentalMapper.mapToRentalDto(rental);
    }

    public void deleteRentalById(Long rentalId) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        Car car = rental.getCar();
        car.setAvailable(true);
        carRepository.save(car);
        rentalRepository.delete(rental);
    }
}
