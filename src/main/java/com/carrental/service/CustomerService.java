package com.carrental.service;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.domain.Customer;
import com.carrental.domain.dto.CustomerDto;
import com.carrental.mapper.CustomerMapper;
import com.carrental.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDto> getCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerMapper.mapToCustomerDtoList(customerList);
    }

    public CustomerDto getCustomerById(Long customerId) throws CustomerNotFoundException {
        Customer customer =  customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        return customerMapper.mapToCustomerDto(customer);
    }

    public List<CustomerDto> getCustomersByFirstName(String lastName) {
        List<Customer> customers = customerRepository.findByFirstName(lastName);
        return customerMapper.mapToCustomerDtoList(customers);
    }

    public List<CustomerDto> getCustomersByLastName(String lastName) {
        List<Customer> customers = customerRepository.findByLastName(lastName);
        return customerMapper.mapToCustomerDtoList(customers);
    }

    public List<CustomerDto> getCustomersByPhoneNumber(String phoneNumber) {
        List<Customer> customers = customerRepository.findByPhoneNumber(phoneNumber);
        return customerMapper.mapToCustomerDtoList(customers);
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.mapToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.mapToCustomerDto(savedCustomer);
    }

    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) throws CustomerNotFoundException {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow((CustomerNotFoundException::new));

        existingCustomer.setFirstName(customerDto.getFirstName());
        existingCustomer.setLastName(customerDto.getLastName());
        existingCustomer.setAddress(customerDto.getAddress());
        existingCustomer.setPhoneNumber(customerDto.getPhoneNumber());
        existingCustomer.setEmail(customerDto.getEmail());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return customerMapper.mapToCustomerDto(updatedCustomer);
    }
    public void deleteCustomerById(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
    }
}
