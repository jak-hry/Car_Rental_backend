package com.carrental.controller;

import com.carrental.controller.CarController;
import com.carrental.domain.dto.CarDto;
import com.carrental.service.CarService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void testGetDataBaseCars() throws Exception {
        List<CarDto> carList = Arrays.asList(
                new CarDto(1L, "Model 1", true),
                new CarDto(2L, "Model 2", true)
        );

        Mockito.when(carService.getDataBaseCarList()).thenReturn(carList);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model", Matchers.is("Model 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].available", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].model", Matchers.is("Model 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].available", Matchers.is(true)));

        Mockito.verify(carService, Mockito.times(1)).getDataBaseCarList();
        Mockito.verifyNoMoreInteractions(carService);
    }

    @Test
    public void testGetAPICars() throws Exception {
        List<CarDto> carList = Arrays.asList(
                new CarDto(1L, "Model 1", true),
                new CarDto(2L, "Model 2", true)
        );

        Mockito.when(carService.getAPICarList()).thenReturn(carList);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cars/api"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model", Matchers.is("Model 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].available", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].model", Matchers.is("Model 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].available", Matchers.is(true)));

        Mockito.verify(carService, Mockito.times(1)).getAPICarList();
        Mockito.verifyNoMoreInteractions(carService);
    }

    @Test
    public void testGetAvailableCars() throws Exception {
        List<CarDto> carList = Arrays.asList(
                new CarDto(1L, "Model 1", true),
                new CarDto(2L, "Model 2", true)
        );

        Mockito.when(carService.getAvailableCars()).thenReturn(carList);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cars/available"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model", Matchers.is("Model 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].available", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].model", Matchers.is("Model 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].available", Matchers.is(true)));

        Mockito.verify(carService, Mockito.times(1)).getAvailableCars();
        Mockito.verifyNoMoreInteractions(carService);
    }

    @Test
    public void testGetCarById() throws Exception {
        CarDto carDto = new CarDto(1L, "Model 1", true);

        Mockito.when(carService.getCarById(1L)).thenReturn(carDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cars/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("Model 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.available", Matchers.is(true)));

        Mockito.verify(carService, Mockito.times(1)).getCarById(1L);
        Mockito.verifyNoMoreInteractions(carService);
    }

    @Test
    public void testGetCarByModel() throws Exception {
        CarDto carDto = new CarDto(1L, "Model 1");

        Mockito.when(carService.getCarByModel("Model 1")).thenReturn(carDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cars?model=Model 1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("Model 1")));

        Mockito.verify(carService, Mockito.times(1)).getCarByModel("Model 1");
        Mockito.verifyNoMoreInteractions(carService);
    }

    @Test
    public void testSaveCar() throws Exception {
        CarDto carDto = new CarDto(1L, "Model 1", true);

        Mockito.when(carService.saveCar(Mockito.any(CarDto.class))).thenReturn(carDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"model\":\"Model 1\",\"available\":true}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("Model 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.available", Matchers.is(true)));

        Mockito.verify(carService, Mockito.times(1)).saveCar(Mockito.any(CarDto.class));
        Mockito.verifyNoMoreInteractions(carService);
    }

    @Test
    public void testUpdateCar() throws Exception {
        CarDto carDto = new CarDto(1L, "Model 1", true);

        Mockito.when(carService.updateCar(Mockito.any(CarDto.class))).thenReturn(carDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/cars/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"model\":\"Model 1\",\"available\":true}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("Model 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.available", Matchers.is(true)));

        Mockito.verify(carService, Mockito.times(1)).updateCar(Mockito.any(CarDto.class));
        Mockito.verifyNoMoreInteractions(carService);
    }

    @Test
    public void testDeleteCar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/cars/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(carService, Mockito.times(1)).deleteCarById(1L);
        Mockito.verifyNoMoreInteractions(carService);
    }
}