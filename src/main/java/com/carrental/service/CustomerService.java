package com.carrental.service;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.domain.Customer;
import com.carrental.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer getCustomerById(Long customerId) throws CustomerNotFoundException {
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }

    public List<Customer> getCustomersByFirstName(String lastName) {
        return customerRepository.findByFirstName(lastName);
    }

    public List<Customer> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    public Customer getCustomersByPhoneNumber(String phoneNumber) throws CustomerNotFoundException {
        return customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
    }
}
