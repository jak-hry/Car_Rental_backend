package com.carrental.service;

import com.carrental.controller.exceptions.CustomerNotFoundException;
import com.carrental.domain.dto.CustomerDto;
import com.carrental.facade.CustomerFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceTest {

    @Mock
    private CustomerFacade customerFacade;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCustomers() {
        // Given
        List<CustomerDto> expectedCustomerDtoList = new ArrayList<>();
        when(customerFacade.getCustomers()).thenReturn(expectedCustomerDtoList);

        // When
        List<CustomerDto> result = customerService.getCustomers();

        // Then
        assertEquals(expectedCustomerDtoList, result);
    }

    @Test
    public void getCustomerById() throws CustomerNotFoundException {
        // Given
        Long customerId = 1L;
        CustomerDto expectedCustomerDto = new CustomerDto(customerId, "John", "Doe");
        when(customerFacade.getCustomerById(customerId)).thenReturn(expectedCustomerDto);

        // When
        CustomerDto result = customerService.getCustomerById(customerId);

        // Then
        assertEquals(expectedCustomerDto, result);
    }

    @Test
    public void getCustomersByFirstName() {
        // Given
        String firstName = "John";
        List<CustomerDto> expectedCustomerDtoList = new ArrayList<>();
        when(customerFacade.getCustomersByFirstName(firstName)).thenReturn(expectedCustomerDtoList);

        // When
        List<CustomerDto> result = customerService.getCustomersByFirstName(firstName);

        // Then
        assertEquals(expectedCustomerDtoList, result);
    }

    @Test
    public void getCustomersByLastName() {
        // Given
        String lastName = "Doe";
        List<CustomerDto> expectedCustomerDtoList = new ArrayList<>();
        when(customerFacade.getCustomersByLastName(lastName)).thenReturn(expectedCustomerDtoList);

        // When
        List<CustomerDto> result = customerService.getCustomersByLastName(lastName);

        // Then
        assertEquals(expectedCustomerDtoList, result);
    }

    @Test
    public void getCustomersByPhoneNumber() {
        // Given
        String phoneNumber = "123456789";
        List<CustomerDto> expectedCustomerDtoList = new ArrayList<>();
        when(customerFacade.getCustomersByPhoneNumber(phoneNumber)).thenReturn(expectedCustomerDtoList);

        // When
        List<CustomerDto> result = customerService.getCustomersByPhoneNumber(phoneNumber);

        // Then
        assertEquals(expectedCustomerDtoList, result);
    }

    @Test
    public void saveCustomer() {
        // Given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Doe");
        CustomerDto expectedSavedCustomerDto = new CustomerDto(1L, "John", "Doe");
        when(customerFacade.saveCustomer(customerDto)).thenReturn(expectedSavedCustomerDto);

        // When
        CustomerDto result = customerService.saveCustomer(customerDto);

        // Then
        assertEquals(expectedSavedCustomerDto, result);
    }

    @Test
    public void updateCustomer() throws CustomerNotFoundException {
        // Given
        Long customerId = 1L;
        CustomerDto customerDto = new CustomerDto(customerId, "John", "Doe");
        CustomerDto expectedUpdatedCustomerDto = new CustomerDto(customerId, "Updated John", "Updated Doe");
        when(customerFacade.updateCustomer(customerId, customerDto)).thenReturn(expectedUpdatedCustomerDto);

        // When
        CustomerDto result = customerService.updateCustomer(customerId, customerDto);

        // Then
        assertEquals(expectedUpdatedCustomerDto, result);
    }

    @Test
    public void deleteCustomerById() throws CustomerNotFoundException {
        // Given
        Long customerId = 1L;

        // When
        customerService.deleteCustomerById(customerId);

        // Then
        verify(customerFacade, times(1)).deleteCustomerById(customerId);
    }
}