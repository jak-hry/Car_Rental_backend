package com.carrental.repository;

import com.carrental.domain.Car;
import com.carrental.domain.DamagePenalty;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DamagePenaltyRepository extends CrudRepository<DamagePenalty, Long> {
    List<DamagePenalty> findAll();
    Optional<DamagePenalty> findById(Long id);
}