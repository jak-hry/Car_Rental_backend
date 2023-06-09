package com.carrental.controller;

import com.carrental.controller.TransmissionTypeController;
import com.carrental.domain.dto.CarDto;
import com.carrental.domain.dto.TransmissionTypeDto;
import com.carrental.service.TransmissionTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(TransmissionTypeController.class)
public class TransmissionTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransmissionTypeService transmissionTypeService;

    @Test
    public void testGetAllTransmissionTypes() throws Exception {
        List<TransmissionTypeDto> transmissionTypes = Arrays.asList(
                new TransmissionTypeDto(1, "Automatic"),
                new TransmissionTypeDto(2, "Manual")
        );

        when(transmissionTypeService.getAllTransmissionTypes())
                .thenReturn(transmissionTypes);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/transmission")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Automatic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Manual"));
    }

    @Test
    public void testGetCarsByTransmissionTypeWithInvalidTransmissionTypeId_ReturnsEmptyList() throws Exception {
        int transmissionTypeId = 999;
        TransmissionTypeDto transmissionTypeDto = new TransmissionTypeDto(transmissionTypeId, null);

        when(transmissionTypeService.getCarsByTransmissionType(transmissionTypeDto)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/transmission/cars")
                        .param("transmissionTypeId", String.valueOf(transmissionTypeId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }
}