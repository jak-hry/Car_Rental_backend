package com.carrental.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAR")
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "MODEL", nullable = false)
    private String model;
    @Column(name = "AVAILABLE", nullable = false)
    private boolean available;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;

    public Car(Long id, String model, boolean available) {
        this.id = id;
        this.model = model;
        this.available = available;
    }

    public Car(Long id, String model) {
        this.id = id;
        this.model = model;
    }
}