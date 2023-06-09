package com.carrental.domain;

public enum TransmissionType {
    AUTOMATIC("Automatic"),
    MANUAL("Manual");

    private final String name;

    TransmissionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}