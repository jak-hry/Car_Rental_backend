package com.carrental.controller;

import com.carrental.domain.dto.DamagePenaltyDto;
import com.carrental.domain.dto.RentalDto;
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


@WebMvcTest(RentalController.class)
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RentalService rentalService;

    @Test
    void testGetRentalList() throws Exception {
        RentalDto rentalDto1 = RentalDto.builder().id(1L).build();
        RentalDto rentalDto2 = RentalDto.builder().id(2L).build();
        List<RentalDto> rentalList = Arrays.asList(rentalDto1, rentalDto2);

        when(rentalService.getRentalList()).thenReturn(rentalList);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/rentals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(rentalDto1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(rentalDto2.getId()));
    }

    @Test
    void testGetRentalById() throws Exception {
        Long rentalId = 1L;
        RentalDto rentalDto = RentalDto.builder().id(rentalId).build();

        when(rentalService.getRentalById(rentalId)).thenReturn(rentalDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/rentals/{rentalId}", rentalId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(rentalId));
    }

    @Test
    void testSaveRental() throws Exception {
        RentalDto rentalDto = RentalDto.builder().id(1L).build();

        when(rentalService.saveRental(rentalDto)).thenReturn(rentalDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/rentals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(rentalDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(rentalDto.getId()));
    }

    @Test
    void testAddDamageToRental() throws Exception {
        Long rentalId = 1L;
        DamagePenaltyDto damagePenaltyDto = new DamagePenaltyDto();
        RentalDto updatedRentalDto = new RentalDto();

        when(rentalService.addDamageToRental(rentalId, damagePenaltyDto)).thenReturn(updatedRentalDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/rentals/{rentalId}/add-damage", rentalId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(damagePenaltyDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedRentalDto.getId()));
    }

    @Test
    void testRemoveDamageFromRental() throws Exception {
        Long rentalId = 1L;
        RentalDto updatedRentalDto = new RentalDto();

        when(rentalService.removeDamageFromRental(rentalId)).thenReturn(updatedRentalDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/rentals/{rentalId}/remove-damage", rentalId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedRentalDto.getId()));
    }

    @Test
    void testUpdateRental() throws Exception {
        Long rentalId = 1L;
        RentalDto rentalDto = RentalDto.builder().id(rentalId).build();

        when(rentalService.updateRental(rentalDto)).thenReturn(rentalDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/rentals/{id}", rentalId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(rentalDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(rentalId));
    }

    @Test
    void testDeleteRental() throws Exception {
        Long rentalId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/rentals/{rentalId}", rentalId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}