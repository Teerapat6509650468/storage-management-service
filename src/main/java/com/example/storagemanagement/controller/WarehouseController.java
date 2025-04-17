package com.example.storagemanagement.controller;

import com.example.storagemanagement.model.Product;
import com.example.storagemanagement.model.Warehouse;
import com.example.storagemanagement.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    // Get all products in the warehouse
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return warehouseService.getAllProducts();
    }

    // Add a product to the warehouse (check-in)
    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = warehouseService.addProduct(product);
        return ResponseEntity.ok(addedProduct);
    }

    // Remove a product from the warehouse (check-out)
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long productId) {
        warehouseService.removeProduct(productId);
        return ResponseEntity.noContent().build();
    }

    // Get warehouse information
    @GetMapping("/area")
    public Warehouse getWarehouse() {
        return warehouseService.getWarehouse();
    }

    // Initialize the warehouse (only once)
    @PostMapping("/area")
    public ResponseEntity<Void> initializeWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.initializeWarehouse(warehouse.getTotalArea());
        return ResponseEntity.ok().build();
    }
}
