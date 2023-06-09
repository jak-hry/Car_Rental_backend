package com.carrental.controller;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.domain.dto.CustomerDto;
import com.carrental.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long customerId) throws CustomerNotFoundException {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @GetMapping(params = "lastName")
    public ResponseEntity<List<CustomerDto>> getCustomersByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(customerService.getCustomersByLastName(lastName));
    }

    @GetMapping(params = "firstName")
    public ResponseEntity<List<CustomerDto>> getCustomersByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(customerService.getCustomersByFirstName(firstName));
    }

    @GetMapping(params = "phoneNumber")
    public ResponseEntity<List<CustomerDto>> getCustomerByPhoneNumber(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(customerService.getCustomersByPhoneNumber(phoneNumber));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomerDto = customerService.saveCustomer(customerDto);
        return ResponseEntity.ok(createdCustomerDto);
    }

    @PutMapping(value = "{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto customerDto)
            throws CustomerNotFoundException {
        customerDto.setId(customerId);
        CustomerDto updatedCustomer = customerService.updateCustomer(customerId, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping(value = "{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) throws CustomerNotFoundException {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.ok().build();
    }
}