package com.example.storagemanagement.controller;

import com.example.storagemanagement.model.Product;
import com.example.storagemanagement.model.WarehouseArea;
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
    public ResponseEntity<Void> removeProduct(@PathVariable String productId) {
        warehouseService.removeProduct(productId);
        return ResponseEntity.noContent().build();
    }

    // Get warehouse area information
    @GetMapping("/area")
    public WarehouseArea getWarehouseArea() {
        return warehouseService.getWarehouseArea();
    }
    
    /*
    @PostMapping("/area")
    public ResponseEntity<Void> initializeWarehouseArea(@RequestBody WarehouseArea warehouseArea) {
        warehouseService.initializeWarehouseArea(warehouseArea.getTotalArea());
        return ResponseEntity.ok().build();
    }*/

    // Initialize the warehouse area (only once)
    @PostMapping("/area")
    public ResponseEntity<Void> initializeWarehouseArea(@RequestBody WarehouseArea warehouseArea) {
        warehouseService.initializeWarehouseArea(warehouseArea);
        return ResponseEntity.ok().build();
    }
}
