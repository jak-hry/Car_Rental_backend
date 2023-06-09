package com.carrental.mapper;

import com.carrental.domain.Customer;
import com.carrental.domain.dto.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerMapperTest {

    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerMapper = new CustomerMapper();
    }

    @Test
    void shouldMapCustomerDtoToCustomer() {
        // Given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setFirstName("John");
        customerDto.setLastName("Doe");
        customerDto.setAddress("123 Main St");
        customerDto.setPhoneNumber("555-1234");
        customerDto.setEmail("john.doe@example.com");

        // When
        Customer customer = customerMapper.mapToCustomer(customerDto);

        // Then
        assertNotNull(customer);
        assertEquals(customerDto.getId(), customer.getId());
        assertEquals(customerDto.getFirstName(), customer.getFirstName());
        assertEquals(customerDto.getLastName(), customer.getLastName());
        assertEquals(customerDto.getAddress(), customer.getAddress());
        assertEquals(customerDto.getPhoneNumber(), customer.getPhoneNumber());
        assertEquals(customerDto.getEmail(), customer.getEmail());
    }

    @Test
    void shouldMapCustomerToCustomerDto() {
        // Given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setAddress("123 Main St");
        customer.setPhoneNumber("555-1234");
        customer.setEmail("john.doe@example.com");

        // When
        CustomerDto customerDto = customerMapper.mapToCustomerDto(customer);

        // Then
        assertNotNull(customerDto);
        assertEquals(customer.getId(), customerDto.getId());
        assertEquals(customer.getFirstName(), customerDto.getFirstName());
        assertEquals(customer.getLastName(), customerDto.getLastName());
        assertEquals(customer.getAddress(), customerDto.getAddress());
        assertEquals(customer.getPhoneNumber(), customerDto.getPhoneNumber());
        assertEquals(customer.getEmail(), customerDto.getEmail());
    }

    @Test
    void shouldMapCustomerListToCustomerDtoList() {
        // Given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setAddress("123 Main St");
        customer1.setPhoneNumber("555-1234");
        customer1.setEmail("john.doe@example.com");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");
        customer2.setAddress("456 Elm St");
        customer2.setPhoneNumber("555-5678");
        customer2.setEmail("jane.smith@example.com");

        List<Customer> customers = List.of(customer1, customer2);

        // When
        List<CustomerDto> customerDtoList = customerMapper.mapToCustomerDtoList(customers);

        // Then
        assertNotNull(customerDtoList);
        assertEquals(customers.size(), customerDtoList.size());

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            CustomerDto customerDto = customerDtoList.get(i);

            assertEquals(customer.getId(), customerDto.getId());
            assertEquals(customer.getFirstName(), customerDto.getFirstName());
            assertEquals(customer.getLastName(), customerDto.getLastName());
            assertEquals(customer.getAddress(), customerDto.getAddress());
            assertEquals(customer.getPhoneNumber(), customerDto.getPhoneNumber());
            assertEquals(customer.getEmail(), customerDto.getEmail());
        }
    }
}