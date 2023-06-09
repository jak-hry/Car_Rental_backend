package com.carrental.controller;

import com.carrental.domain.dto.DamagePenaltyDto;
import com.carrental.domain.dto.RentalDto;
import com.carrental.service.DamagePenaltyService;
import com.carrental.service.RentalService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.Mockito.when;

@WebMvcTest(DamagePenaltyController.class)
class DamagePenaltyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DamagePenaltyService damagePenaltyService;

    @MockBean
    private RentalService rentalService;

    @Test
    void testCreateDamagePenalty() throws Exception {
        Long rentalId = 1L;
        Long damagePenaltyId = 1L;
        DamagePenaltyDto damagePenaltyDto = new DamagePenaltyDto();
        RentalDto rentalDto = new RentalDto();
        damagePenaltyDto.setRental(rentalDto);
        DamagePenaltyDto createdDamagePenaltyDto = new DamagePenaltyDto();
        createdDamagePenaltyDto.setId(damagePenaltyId);

        when(rentalService.getRentalById(rentalId)).thenReturn(rentalDto);
        when(damagePenaltyService.createDamagePenalty(damagePenaltyDto, rentalDto)).thenReturn(createdDamagePenaltyDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/damage-penalties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(damagePenaltyDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllDamagePenalties() throws Exception {
        DamagePenaltyDto damagePenaltyDto1 = new DamagePenaltyDto();
        DamagePenaltyDto damagePenaltyDto2 = new DamagePenaltyDto();
        List<DamagePenaltyDto> damagePenalties = Arrays.asList(damagePenaltyDto1, damagePenaltyDto2);

        when(damagePenaltyService.getAllDamagePenalties()).thenReturn(damagePenalties);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/damage-penalties")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(damagePenaltyDto1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(damagePenaltyDto2.getId()));
    }

    @Test
    void testGetDamagePenaltyById() throws Exception {
        Long damagePenaltyId = 1L;
        DamagePenaltyDto damagePenaltyDto = new DamagePenaltyDto();
        damagePenaltyDto.setId(damagePenaltyId);

        when(damagePenaltyService.getDamagePenaltyById(damagePenaltyId)).thenReturn(damagePenaltyDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/damage-penalties/{id}", damagePenaltyId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(damagePenaltyId));
    }

    @Test
    void testDeleteDamagePenaltyById() throws Exception {
        Long damagePenaltyId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/damage-penalties/{id}", damagePenaltyId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
