package com.carrental.controller;

import com.carrental.controller.CarCategoryController;
import com.carrental.domain.CarCategory;
import com.carrental.domain.dto.CarCategoryDto;
import com.carrental.domain.dto.CarDto;
import com.carrental.service.CarCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@WebMvcTest(CarCategoryController.class)
class CarCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarCategoryService carCategoryService;

    @Test
    public void testGetCarsByCategory() throws Exception {
        CarDto carDto1 = CarDto.builder()
                .id(1L)
                .model("Model 1")
                .available(true)
                .category(new CarCategoryDto("SEDAN"))
                .build();

        CarDto carDto2 = CarDto.builder()
                .id(2L)
                .model("Model 2")
                .available(true)
                .category(new CarCategoryDto("SEDAN"))
                .build();

        List<CarDto> cars = Arrays.asList(carDto1, carDto2);

        when(carCategoryService.getCarsByCategory(eq(CarCategory.SEDAN))).thenReturn(cars);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/category")
                        .param("category", "SEDAN"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value("Model 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].available").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category.name").value("SEDAN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].model").value("Model 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].available").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].category.name").value("SEDAN"));


        verify(carCategoryService, times(1)).getCarsByCategory(eq(CarCategory.SEDAN));
        verifyNoMoreInteractions(carCategoryService);
    }

    @Test
    public void testGetAllCategories() throws Exception {
        CarCategoryDto categoryDto1 = new CarCategoryDto(0, "SEDAN");
        CarCategoryDto categoryDto2 = new CarCategoryDto(1, "SUV");
        List<CarCategoryDto> categories = Arrays.asList(categoryDto1, categoryDto2);

        when(carCategoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/category"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("SEDAN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("SUV"));

        verify(carCategoryService, times(1)).getAllCategories();
        verifyNoMoreInteractions(carCategoryService);
    }
}