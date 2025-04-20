package com.example.storagemanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    private String id;

    private String name;
    private double size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id") // FK to warehouse_area table
    private WarehouseArea warehouse;
    
    // Default constructor (required by JPA)
    public Product() {
    }

    // Optional constructor for easier object creation
    public Product(String name, double size, WarehouseArea warehouse) {
        this.name = name;
        this.size = size;
        this.warehouse = warehouse;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
