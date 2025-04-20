package com.example.storagemanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse")
public class WarehouseArea {

    @Id
    private String id;

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
    public String getId() {
        return id;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getAvailableArea() {
        return availableArea;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public void setAvailableArea(double availableArea) {
        this.availableArea = availableArea;
    }
}
