package com.carrental.repository;

import com.carrental.domain.Customer;
import com.carrental.domain.Rental;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentalRepository extends CrudRepository<Rental, Long> {
    List<Rental> findAll();
    Optional<Rental> findById(Long id);
    List<Rental> findByCustomer(Customer customer);
}