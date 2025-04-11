package com.example.storagemanagement.model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double size;

    // Default constructor (required by JPA)
    public Product() {
    }

    // Optional constructor for easier object creation
    public Product(String name, double size) {
        this.name = name;
        this.size = size;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
