package com.carrental.mapper;

import com.carrental.domain.DamagePenalty;
import com.carrental.domain.dto.DamagePenaltyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DamagePenaltyMapper {

    public DamagePenalty mapToDamagePenalty(DamagePenaltyDto damagePenaltyDto) {
        if (damagePenaltyDto == null) {
            return null;
        }
        return DamagePenalty.builder()
                .id(damagePenaltyDto.getId())
                .description(damagePenaltyDto.getDescription())
                .amount(damagePenaltyDto.getAmount())
                .build();
    }

    public DamagePenaltyDto mapToDamagePenaltyDto(DamagePenalty damagePenalty) {
        if (damagePenalty == null) {
            return null;
        }
        return DamagePenaltyDto.builder()
                .id(damagePenalty.getId())
                .description(damagePenalty.getDescription())
                .amount(damagePenalty.getAmount())
                .build();
    }

    public List<DamagePenaltyDto> mapToDamagePenaltyDtoList(List<DamagePenalty> damagePenalties) {
        return damagePenalties.stream()
                .map(this::mapToDamagePenaltyDto)
                .collect(Collectors.toList());
    }
}