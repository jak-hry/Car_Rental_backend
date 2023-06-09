package com.carrental.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    @Column(name = "AVAILABLE")
    private boolean available;

    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private CarCategory category;

    @Column(name = "TRANSMISSION")
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;
    @Column(name = "COST_PER_DAY")
    private BigDecimal costPerDay;
    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;
    private boolean damaged;
    public Car(Long id, String model, boolean available, CarCategory category, TransmissionType transmissionType, BigDecimal costPerDay, boolean damaged) {
        this.id = id;
        this.model = model;
        this.available = available;
        this.category = category;
        this.transmissionType = transmissionType;
        this.costPerDay = costPerDay;
        this.damaged = damaged;
    }

    public Car(Long id, String model, boolean available, CarCategory category, TransmissionType transmissionType, BigDecimal costPerDay) {
        this.id = id;
        this.model = model;
        this.available = available;
        this.category = category;
        this.transmissionType = transmissionType;
        this.costPerDay = costPerDay;
    }

    public Car(Long id, String model, boolean available) {
        this.id = id;
        this.model = model;
        this.available = available;
    }

    public Car(String model, CarCategory category, TransmissionType transmissionType) {
        this.model = model;
        this.category = category;
        this.transmissionType = transmissionType;
    }

    public Car(Long id, String model) {
        this.id = id;
        this.model = model;
    }
}