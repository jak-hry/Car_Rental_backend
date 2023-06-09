package com.carrental.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DAMAGE_PENALTY")
@Builder
public class DamagePenalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "RENTAL_ID", nullable = false)
    private Rental rental;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
}