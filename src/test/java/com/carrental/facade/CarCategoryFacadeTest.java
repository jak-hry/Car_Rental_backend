package com.carrental.facade;

import com.carrental.domain.Car;
import com.carrental.domain.CarCategory;
import com.carrental.domain.dto.CarCategoryDto;
import com.carrental.domain.dto.CarDto;
import com.carrental.mapper.CarMapper;
import com.carrental.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarCategoryFacadeTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;
    private CarCategoryFacade categoryFacade;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryFacade = new CarCategoryFacade(carRepository, carMapper);
    }

    @Test
    void shouldGetCarsByCategory() {
        // Given
        CarCategory category = CarCategory.SEDAN;

        Car car1 = new Car();
        car1.setId(1L);
        car1.setModel("Toyota Camry");
        car1.setCategory(category);

        Car car2 = new Car();
        car2.setId(2L);
        car2.setModel("Honda Civic");
        car2.setCategory(category);

        List<Car> cars = Arrays.asList(car1, car2);

        CarDto carDto1 = new CarDto();
        carDto1.setId(1L);
        carDto1.setModel("Toyota Camry");

        CarDto carDto2 = new CarDto();
        carDto2.setId(2L);
        carDto2.setModel("Honda Civic");

        when(carRepository.findByCategory(category)).thenReturn(cars);
        when(carMapper.mapToCarDtoList(cars)).thenReturn(Arrays.asList(carDto1, carDto2));

        // When
        List<CarDto> result = categoryFacade.getCarsByCategory(category);

        // Then
        assertEquals(2, result.size());
        assertEquals(carDto1.getId(), result.get(0).getId());
        assertEquals(carDto1.getModel(), result.get(0).getModel());
        assertEquals(carDto2.getId(), result.get(1).getId());
        assertEquals(carDto2.getModel(), result.get(1).getModel());
    }

    @Test
    void shouldGetAllCategories() {
        // When
        List<CarCategoryDto> result = categoryFacade.getAllCategories();

        // Then
        List<CarCategoryDto> expected = Arrays.stream(CarCategory.values())
                .map(category -> new CarCategoryDto(category.ordinal(), category.name())).toList();

        assertEquals(expected.size(), result.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getId(), result.get(i).getId());
            assertEquals(expected.get(i).getName(), result.get(i).getName());
        }
    }
}