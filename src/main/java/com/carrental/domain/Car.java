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

    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Column(name = "MODEL", nullable = false)
    private String model;

    @Column(name = "YEAR", nullable = false)
    private int year;

    @Column(name = "REGISTRATION_NUMBER", nullable = false)
    private String registrationNumber;

    @Column(name = "COLOR", nullable = false)
    private String color;

    @Column(name = "RENTAL_PRICE_PER_DAY", nullable = false)
    private BigDecimal rentalPricePerDay;

    @Column(name = "AVAILABLE", nullable = false)
    private boolean available;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;

    public Car(Long id, String brand, String model, int year, String registrationNumber, String color, BigDecimal rentalPricePerDay, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.rentalPricePerDay = rentalPricePerDay;
        this.available = available;
    }
}