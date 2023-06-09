package com.carrental.repository;

import com.carrental.domain.Car;
import com.carrental.domain.CarCategory;
import com.carrental.domain.TransmissionType;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();
    List<Car> findByCategory(CarCategory carCategory);
    List<Car> findByTransmissionType(TransmissionType transmissionType);
    List<Car> findByAvailable(boolean available);
    Optional<Car> findById(Long id);
    Optional<Car> findByModel(String model);
}