package com.carrental.repository;

import com.carrental.domain.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();
    Optional<Car> findById(Long id);
    List<Car> findByBrand(String brand);
    List<Car> findByModel(String model);
}