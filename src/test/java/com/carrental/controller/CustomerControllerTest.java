package com.carrental.controller;

import com.carrental.controller.CustomerController;
import com.carrental.domain.dto.CustomerDto;
import com.carrental.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testGetCustomers() throws Exception {
        CustomerDto customerDto1 = CustomerDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .phoneNumber("555-1234")
                .email("john.doe@example.com")
                .build();

        CustomerDto customerDto2 = CustomerDto.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .address("456 Oak St")
                .phoneNumber("555-5678")
                .email("jane.smith@example.com")
                .build();

        List<CustomerDto> customers = Arrays.asList(customerDto1, customerDto2);

        when(customerService.getCustomers()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("123 Main St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value("555-1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address").value("456 Oak St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phoneNumber").value("555-5678"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("jane.smith@example.com"));

        verify(customerService, times(1)).getCustomers();
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testGetCustomerById() throws Exception {
        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .phoneNumber("555-1234")
                .email("john.doe@example.com")
                .build();

        when(customerService.getCustomerById(eq(1L))).thenReturn(customerDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("123 Main St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("555-1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"));

        verify(customerService, times(1)).getCustomerById(eq(1L));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testGetCustomersByLastName() throws Exception {
        CustomerDto customerDto1 = CustomerDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .phoneNumber("555-1234")
                .email("john.doe@example.com")
                .build();

        CustomerDto customerDto2 = CustomerDto.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Doe")
                .address("456 Oak St")
                .phoneNumber("555-5678")
                .email("jane.doe@example.com")
                .build();

        List<CustomerDto> customers = Arrays.asList(customerDto1, customerDto2);

        when(customerService.getCustomersByLastName(eq("Doe"))).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/customers").param("lastName", "Doe"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("123 Main St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value("555-1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address").value("456 Oak St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phoneNumber").value("555-5678"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("jane.doe@example.com"));

        verify(customerService, times(1)).getCustomersByLastName(eq("Doe"));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testGetCustomersByFirstName() throws Exception {
        CustomerDto customerDto1 = CustomerDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .phoneNumber("555-1234")
                .email("john.doe@example.com")
                .build();

        CustomerDto customerDto2 = CustomerDto.builder()
                .id(2L)
                .firstName("John")
                .lastName("Smith")
                .address("456 Oak St")
                .phoneNumber("555-5678")
                .email("john.smith@example.com")
                .build();

        List<CustomerDto> customers = Arrays.asList(customerDto1, customerDto2);

        when(customerService.getCustomersByFirstName(eq("John"))).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/customers").param("firstName", "John"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("123 Main St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value("555-1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address").value("456 Oak St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phoneNumber").value("555-5678"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("john.smith@example.com"));

        verify(customerService, times(1)).getCustomersByFirstName(eq("John"));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testGetCustomersByPhoneNumber() throws Exception {
        CustomerDto customerDto1 = CustomerDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .phoneNumber("555-1234")
                .email("john.doe@example.com")
                .build();

        CustomerDto customerDto2 = CustomerDto.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .address("456 Oak St")
                .phoneNumber("555-1234")
                .email("jane.smith@example.com")
                .build();

        List<CustomerDto> customers = Arrays.asList(customerDto1, customerDto2);

        when(customerService.getCustomersByPhoneNumber(eq("555-1234"))).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/customers").param("phoneNumber", "555-1234"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("123 Main St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value("555-1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address").value("456 Oak St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phoneNumber").value("555-1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("jane.smith@example.com"));

        verify(customerService, times(1)).getCustomersByPhoneNumber(eq("555-1234"));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .phoneNumber("555-1234")
                .email("john.doe@example.com")
                .build();

        when(customerService.saveCustomer(any(CustomerDto.class))).thenReturn(customerDto);

        String requestBody = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"address\": \"123 Main St\", \"phoneNumber\": \"555-1234\", \"email\": \"john.doe@example.com\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("123 Main St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("555-1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"));

        verify(customerService, times(1)).saveCustomer(any(CustomerDto.class));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .phoneNumber("555-1234")
                .email("john.doe@example.com")
                .build();

        when(customerService.updateCustomer(eq(1L), any(CustomerDto.class))).thenReturn(customerDto);

        String requestBody = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"address\": \"123 Main St\", \"phoneNumber\": \"555-1234\", \"email\": \"john.doe@example.com\" }";

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("123 Main St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("555-1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"));

        verify(customerService, times(1)).updateCustomer(eq(1L), any(CustomerDto.class));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(customerService, times(1)).deleteCustomerById(eq(1L));
        verifyNoMoreInteractions(customerService);
    }
}