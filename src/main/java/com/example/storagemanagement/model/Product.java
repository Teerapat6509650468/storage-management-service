package com.example.storagemanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id") // FK to warehouse table
    private Warehouse warehouse;
    
    // Default constructor (required by JPA)
    public Product() {
    }

    // Optional constructor for easier object creation
    public Product(String name, double size, Warehouse warehouse) {
        this.name = name;
        this.size = size;
        this.warehouse = warehouse;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
