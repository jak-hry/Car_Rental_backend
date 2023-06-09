package com.carrental.mapper;

import com.carrental.domain.DamagePenalty;
import com.carrental.domain.dto.DamagePenaltyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DamagePenaltyMapperTest {

    private DamagePenaltyMapper damagePenaltyMapper;

    @BeforeEach
    void setUp() {
        damagePenaltyMapper = new DamagePenaltyMapper();
    }

    @Test
    void testMapToDamagePenalty() {
        // Given
        DamagePenaltyDto damagePenaltyDto = new DamagePenaltyDto();
        damagePenaltyDto.setId(1L);
        damagePenaltyDto.setDescription("Test Description");
        damagePenaltyDto.setAmount(BigDecimal.ONE);

        // When
        DamagePenalty result = damagePenaltyMapper.mapToDamagePenalty(damagePenaltyDto);

        // Then
        assertNotNull(result);
        assertEquals(damagePenaltyDto.getId(), result.getId());
        assertEquals(damagePenaltyDto.getDescription(), result.getDescription());
        assertEquals(damagePenaltyDto.getAmount(), result.getAmount());
    }

    @Test
    void testMapToDamagePenaltyWithNullDto() {
        // When
        DamagePenalty result = damagePenaltyMapper.mapToDamagePenalty(null);

        // Then
        assertNull(result);
    }

    @Test
    void testMapToDamagePenaltyDto() {
        // Given
        DamagePenalty damagePenalty = DamagePenalty.builder()
                .id(1L)
                .description("Test Description")
                .amount(BigDecimal.ONE)
                .build();

        // When
        DamagePenaltyDto result = damagePenaltyMapper.mapToDamagePenaltyDto(damagePenalty);

        // Then
        assertNotNull(result);
        assertEquals(damagePenalty.getId(), result.getId());
        assertEquals(damagePenalty.getDescription(), result.getDescription());
        assertEquals(damagePenalty.getAmount(), result.getAmount());
    }

    @Test
    void testMapToDamagePenaltyDtoWithNullEntity() {
        // When
        DamagePenaltyDto result = damagePenaltyMapper.mapToDamagePenaltyDto(null);

        // Then
        assertNull(result);
    }

    @Test
    void testMapToDamagePenaltyDtoList() {
        // Given
        DamagePenalty damagePenalty1 = DamagePenalty.builder()
                .id(1L)
                .description("Description 1")
                .amount(BigDecimal.ONE)
                .build();

        DamagePenalty damagePenalty2 = DamagePenalty.builder()
                .id(2L)
                .description("Description 2")
                .amount(BigDecimal.ONE)
                .build();

        List<DamagePenalty> damagePenalties = Arrays.asList(damagePenalty1, damagePenalty2);

        // When
        List<DamagePenaltyDto> result = damagePenaltyMapper.mapToDamagePenaltyDtoList(damagePenalties);

        // Then
        assertNotNull(result);
        assertEquals(damagePenalties.size(), result.size());

        assertEquals(damagePenalty1.getId(), result.get(0).getId());
        assertEquals(damagePenalty1.getDescription(), result.get(0).getDescription());
        assertEquals(damagePenalty1.getAmount(), result.get(0).getAmount());

        assertEquals(damagePenalty2.getId(), result.get(1).getId());
        assertEquals(damagePenalty2.getDescription(), result.get(1).getDescription());
        assertEquals(damagePenalty2.getAmount(), result.get(1).getAmount());
    }

    @Test
    void testMapToDamagePenaltyDtoListWithEmptyList() {
        // Given
        List<DamagePenalty> damagePenalties = List.of();

        // When
        List<DamagePenaltyDto> result = damagePenaltyMapper.mapToDamagePenaltyDtoList(damagePenalties);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}