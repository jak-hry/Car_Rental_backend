package com.carrental.facade;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.domain.Customer;
import com.carrental.domain.dto.CustomerDto;
import com.carrental.mapper.CustomerMapper;
import com.carrental.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerFacadeTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;
    private CustomerFacade customerFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerFacade = new CustomerFacade(customerRepository,
                customerMapper);
    }

    @Test
    void shouldGetCustomers() {
        // Given

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");

        CustomerDto customerDto1 = new CustomerDto();
        customerDto1.setId(1L);
        customerDto1.setFirstName("John");
        customerDto1.setLastName("Doe");

        List<Customer> customers = Collections.singletonList(customer1);
        List<CustomerDto> expected = Collections.singletonList(customerDto1);

        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.mapToCustomerDtoList(customers)).thenReturn(expected);

        // When
        List<CustomerDto> result = customerFacade.getCustomers();

        // Then
        assertEquals(expected.size(), result.size());
        assertEquals(expected.get(0).getId(), result.get(0).getId());
        assertEquals(expected.get(0).getFirstName(), result.get(0).getFirstName());
        assertEquals(expected.get(0).getLastName(), result.get(0).getLastName());
    }

    @Test
    void shouldGetCustomerById() throws CustomerNotFoundException {
        // Given
        Long customerId = 1L;

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("John");
        customer.setLastName("Doe");

        CustomerDto expected = new CustomerDto();
        expected.setId(customerId);
        expected.setFirstName("John");
        expected.setLastName("Doe");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.mapToCustomerDto(customer)).thenReturn(expected);

        // When
        CustomerDto result = customerFacade.getCustomerById(customerId);

        // Then
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getLastName(), result.getLastName());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        // Given
        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(CustomerNotFoundException.class, () -> customerFacade.getCustomerById(customerId));
    }

    @Test
    void shouldGetCustomersByFirstName() {
        // Given
        String firstName = "John";

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName(firstName);
        customer1.setLastName("Doe");

        CustomerDto customerDto1 = new CustomerDto();
        customerDto1.setId(1L);
        customerDto1.setFirstName(firstName);
        customerDto1.setLastName("Doe");

        List<Customer> customers = Collections.singletonList(customer1);
        List<CustomerDto> expected = Collections.singletonList(customerDto1);

        when(customerRepository.findByFirstName(firstName)).thenReturn(customers);
        when(customerMapper.mapToCustomerDtoList(customers)).thenReturn(expected);

        // When
        List<CustomerDto> result = customerFacade.getCustomersByFirstName(firstName);

        // Then
        assertEquals(expected.size(), result.size());
        assertEquals(expected.get(0).getId(), result.get(0).getId());
        assertEquals(expected.get(0).getFirstName(), result.get(0).getFirstName());
        assertEquals(expected.get(0).getLastName(), result.get(0).getLastName());
    }

    @Test
    void shouldGetCustomersByLastName() {
        // Given
        String lastName = "Doe";

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("John");
        customer1.setLastName(lastName);

        CustomerDto customerDto1 = new CustomerDto();
        customerDto1.setId(1L);
        customerDto1.setFirstName("John");
        customerDto1.setLastName(lastName);

        List<Customer> customers = Collections.singletonList(customer1);
        List<CustomerDto> expected = Collections.singletonList(customerDto1);

        when(customerRepository.findByLastName(lastName)).thenReturn(customers);
        when(customerMapper.mapToCustomerDtoList(customers)).thenReturn(expected);

        // When
        List<CustomerDto> result = customerFacade.getCustomersByLastName(lastName);

        // Then
        assertEquals(expected.size(), result.size());
        assertEquals(expected.get(0).getId(), result.get(0).getId());
        assertEquals(expected.get(0).getFirstName(), result.get(0).getFirstName());
        assertEquals(expected.get(0).getLastName(), result.get(0).getLastName());
    }

    @Test
    void shouldGetCustomersByPhoneNumber() {
        // Given
        String phoneNumber = "1234567890";

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setPhoneNumber(phoneNumber);

        CustomerDto dto = new CustomerDto();
        dto.setId(1L);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setPhoneNumber(phoneNumber);

        List<Customer> customers = Collections.singletonList(customer);
        List<CustomerDto> customersDto = Collections.singletonList(dto);

        when(customerRepository.findByPhoneNumber(phoneNumber)).thenReturn(customers);
        when(customerMapper.mapToCustomerDtoList(customers)).thenReturn(customersDto);

        // When
        List<CustomerDto> result = customerFacade.getCustomersByPhoneNumber(phoneNumber);

        // Then
        assertEquals(1, result.size());
        CustomerDto customerDto = result.get(0);
        assertEquals(customer.getId(), customerDto.getId());
        assertEquals(customer.getFirstName(), customerDto.getFirstName());
        assertEquals(customer.getLastName(), customerDto.getLastName());
        assertEquals(customer.getPhoneNumber(), customerDto.getPhoneNumber());

        verify(customerRepository, times(1)).findByPhoneNumber(phoneNumber);
    }

    @Test
    void shouldSaveCustomer() {
        // Given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("John");
        customerDto.setLastName("Doe");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");

        CustomerDto expected = new CustomerDto();
        expected.setId(1L);
        expected.setFirstName("John");
        expected.setLastName("Doe");

        when(customerMapper.mapToCustomer(customerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.mapToCustomerDto(customer)).thenReturn(expected);

        // When
        CustomerDto result = customerFacade.saveCustomer(customerDto);

        // Then
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getLastName(), result.getLastName());
    }

    @Test
    void shouldUpdateCustomer() throws CustomerNotFoundException {
        // Given
        Long customerId = 1L;

        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("John");
        customerDto.setLastName("Doe");

        Customer existingCustomer = new Customer();
        existingCustomer.setId(customerId);
        existingCustomer.setFirstName("Jane");
        existingCustomer.setLastName("Smith");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(customerId);
        updatedCustomer.setFirstName("John");
        updatedCustomer.setLastName("Doe");

        CustomerDto expected = new CustomerDto();
        expected.setId(customerId);
        expected.setFirstName("John");
        expected.setLastName("Doe");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(existingCustomer)).thenReturn(updatedCustomer);
        when(customerMapper.mapToCustomerDto(updatedCustomer)).thenReturn(expected);

        // When
        CustomerDto result = customerFacade.updateCustomer(customerId, customerDto);

        // Then
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getLastName(), result.getLastName());
    }

    @Test
    void shouldThrowExceptionWhenCustomerToUpdateNotFound() {
        // Given
        Long customerId = 1L;

        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("John");
        customerDto.setLastName("Doe");

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(CustomerNotFoundException.class, () -> customerFacade.updateCustomer(customerId, customerDto));
    }

    @Test
    void shouldDeleteCustomerById() throws CustomerNotFoundException {
        // Given
        Long customerId = 1L;

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("John");
        customer.setLastName("Doe");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // When
        customerFacade.deleteCustomerById(customerId);
    }

    @Test
    void shouldThrowExceptionWhenCustomerToDeleteNotFound() {
        // Given
        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(CustomerNotFoundException.class, () -> customerFacade.deleteCustomerById(customerId));
    }
}