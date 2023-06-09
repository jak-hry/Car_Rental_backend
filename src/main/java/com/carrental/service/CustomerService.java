package com.carrental.service;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.domain.dto.CustomerDto;
import com.carrental.facade.CustomerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerFacade customerFacade;

    public List<CustomerDto> getCustomers() {
        return customerFacade.getCustomers();
    }

    public CustomerDto getCustomerById(Long customerId) throws CustomerNotFoundException {
        return customerFacade.getCustomerById(customerId);
    }

    public List<CustomerDto> getCustomersByFirstName(String firstName) {
        return customerFacade.getCustomersByFirstName(firstName);
    }

    public List<CustomerDto> getCustomersByLastName(String lastName) {
        return customerFacade.getCustomersByLastName(lastName);
    }

    public List<CustomerDto> getCustomersByPhoneNumber(String phoneNumber) {
        return customerFacade.getCustomersByPhoneNumber(phoneNumber);
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return customerFacade.saveCustomer(customerDto);
    }

    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) throws CustomerNotFoundException {
        return customerFacade.updateCustomer(customerId, customerDto);
    }

    public void deleteCustomerById(Long customerId) throws CustomerNotFoundException {
        customerFacade.deleteCustomerById(customerId);
    }
}
