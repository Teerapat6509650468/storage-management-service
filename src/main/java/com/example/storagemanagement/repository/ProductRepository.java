package com.example.storagemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storagemanagement.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
