package com.carrental.repository;

import com.carrental.domain.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}