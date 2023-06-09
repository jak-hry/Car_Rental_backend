package com.carrental.mapper;

import com.carrental.domain.TransmissionType;
import com.carrental.domain.dto.TransmissionTypeDto;
import org.springframework.stereotype.Component;

@Component
public class TransmissionTypeMapper {

    public TransmissionTypeDto toDto(TransmissionType transmissionType) {
        if (transmissionType == null) {
            return null;
        }

        return TransmissionTypeDto.builder()
                .id(transmissionType.ordinal())
                .name(transmissionType.getName())
                .build();
    }

    public TransmissionType toEntity(TransmissionTypeDto transmissionTypeDto) {
        if (transmissionTypeDto == null) {
            return null;
        }

        if (transmissionTypeDto.getId() >= 0 && transmissionTypeDto.getId() < TransmissionType.values().length) {
            return TransmissionType.values()[transmissionTypeDto.getId()];
        }

        return null;
    }
}