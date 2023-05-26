package com.carrental.mapper;

import com.carrental.domain.Customer;
import com.carrental.domain.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerMapper {

    public Customer mapToCustomer(CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getAddress(),
                customerDto.getPhoneNumber(),
                customerDto.getEmail()
        );
    }

    public CustomerDto mapToCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAddress(),
                customer.getPhoneNumber(),
                customer.getEmail()
        );
    }

    public List<CustomerDto> mapToCustomerDtoList(List<Customer> customers) {
        return customers.stream()
                .map(this::mapToCustomerDto)
                .collect(Collectors.toList());
    }
}