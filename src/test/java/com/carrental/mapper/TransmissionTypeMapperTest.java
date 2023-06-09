package com.carrental.mapper;

import com.carrental.domain.TransmissionType;
import com.carrental.domain.dto.TransmissionTypeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransmissionTypeMapperTest {

    private TransmissionTypeMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new TransmissionTypeMapper();
    }

    @Test
    public void testToDtoWithValidTransmissionTypeShouldMapToDto() {
        // Given
        TransmissionType transmissionType = TransmissionType.MANUAL;

        // When
        TransmissionTypeDto dto = mapper.toDto(transmissionType);

        // Then
        assertNotNull(dto);
        assertEquals(transmissionType.ordinal(), dto.getId());
        assertEquals(transmissionType.getName(), dto.getName());
    }

    @Test
    public void testToDtoWithNullTransmissionTypeShouldReturnNull() {
        // When
        TransmissionTypeDto dto = mapper.toDto(null);

        // Then
        assertNull(dto);
    }

    @Test
    public void testToEntityWithValidTransmissionTypeDtoShouldMapToEntity() {
        // Given
        TransmissionTypeDto dto = TransmissionTypeDto.builder()
                .id(1)
                .name("Manual")
                .build();

        // When
        TransmissionType entity = mapper.toEntity(dto);

        // Then
        assertNotNull(entity);
        assertEquals(dto.getId(), entity.ordinal());
        assertEquals(dto.getName(), entity.getName());
    }

    @Test
    public void testToEntityWithNullTransmissionTypeDtoShouldReturnNull() {
        // When
        TransmissionType entity = mapper.toEntity(null);

        // Then
        assertNull(entity);
    }

    @Test
    public void testToEntityWithInvalidTransmissionTypeDtoShouldReturnNull() {
        // Given
        TransmissionTypeDto dto = TransmissionTypeDto.builder()
                .id(100)
                .name("Invalid")
                .build();

        // When
        TransmissionType entity = mapper.toEntity(dto);

        // Then
        assertNull(entity);
    }
}