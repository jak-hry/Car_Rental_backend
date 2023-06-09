package com.carrental.domain;

public enum CarCategory {
    SEDAN("Sedan"),
    SUV("SUV"),
    HATCHBACK("Hatchback"),
    CONVERTIBLE("Convertible"),
    SPORTS_CAR("Sports Car");

    private final String name;

    CarCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}