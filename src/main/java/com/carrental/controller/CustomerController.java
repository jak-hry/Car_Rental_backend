package com.carrental.controller;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.domain.Customer;
import com.carrental.domain.dto.CustomerDto;
import com.carrental.mapper.CustomerMapper;
import com.carrental.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long customerId) throws CustomerNotFoundException {
        return ResponseEntity.ok(customerMapper.mapToCustomerDto(customerService.getCustomerById(customerId)));
    }

    @GetMapping(params = "lastName")
    public ResponseEntity<List<CustomerDto>> getCustomersByLastName(@RequestParam String lastName) {
        List<Customer> customers = customerService.getCustomersByLastName(lastName);
        return ResponseEntity.ok(customerMapper.mapToCustomerDtoList(customers));
    }

    @GetMapping(params = "firstName")
    public ResponseEntity<List<CustomerDto>> getCustomersByFirstName(@RequestParam String firstName) {
        List<Customer> customers = customerService.getCustomersByFirstName(firstName);
        return ResponseEntity.ok(customerMapper.mapToCustomerDtoList(customers));
    }

    @GetMapping("/customers/phone/{phoneNumber}")
    public ResponseEntity<CustomerDto> getCustomerByPhoneNumber(@PathVariable String phoneNumber) throws CustomerNotFoundException {
        Customer customer = customerService.getCustomersByPhoneNumber(phoneNumber);
        CustomerDto customerDto = customerMapper.mapToCustomerDto(customer);
        return ResponseEntity.ok(customerDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerMapper.mapToCustomer(customerDto);
        customerService.saveCustomer(customer);
        return ResponseEntity.ok(customerMapper.mapToCustomerDto(customer));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) throws CustomerNotFoundException {
        Customer customer = customerMapper.mapToCustomer(customerDto);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(customerMapper.mapToCustomerDto(savedCustomer));
    }

    @DeleteMapping(value = "{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) throws CustomerNotFoundException {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.ok().build();
    }
}