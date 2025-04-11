package com.example.storagemanagement.model;

import jakarta.persistence.*;

@Entity
public class WarehouseArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalArea;
    private double availableArea;

    // Default constructor (required by JPA)
    public WarehouseArea() {
    }

    // Optional constructor for easier object creation
    public WarehouseArea(double totalArea, double availableArea) {
        this.totalArea = totalArea;
        this.availableArea = availableArea;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getAvailableArea() {
        return availableArea;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public void setAvailableArea(double availableArea) {
        this.availableArea = availableArea;
    }
}
