package com.example.storagemanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String warehouse_id;

    private double totalArea;
    private double availableArea;

    // Default constructor (required by JPA)
    public Warehouse() {
    }

    // Optional constructor for easier object creation
    public Warehouse(double totalArea, double availableArea) {
        this.totalArea = totalArea;
        this.availableArea = availableArea;
    }

    // Getters and setters
    public String getWarehouse_id() {
        return warehouse_id;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getAvailableArea() {
        return availableArea;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public void setAvailableArea(double availableArea) {
        this.availableArea = availableArea;
    }
}
