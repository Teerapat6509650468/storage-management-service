package com.example.storagemanagement.service;

import com.example.storagemanagement.model.Product;
import com.example.storagemanagement.model.WarehouseArea;
import com.example.storagemanagement.repository.ProductRepository;
import com.example.storagemanagement.repository.WarehouseAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
	
    private final ProductRepository productRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;

    @Autowired
    public WarehouseService(ProductRepository productRepository, WarehouseAreaRepository warehouseAreaRepository) {
        this.productRepository = productRepository;
        this.warehouseAreaRepository = warehouseAreaRepository;
    }

    // Get all products in the warehouse
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Add a product to the warehouse (Check-in)
    public Product addProduct(Product product) {
        // Retrieve the current warehouse area
        Optional<WarehouseArea> warehouseAreaOpt = warehouseAreaRepository.findById(1L);

        if (warehouseAreaOpt.isPresent()) {
            WarehouseArea warehouseArea = warehouseAreaOpt.get();
            double productSize = product.getSize();

            if (productSize <= warehouseArea.getAvailableArea()) {
                // Update warehouse available area
                warehouseArea.setAvailableArea(warehouseArea.getAvailableArea() - productSize);
                warehouseAreaRepository.save(warehouseArea);
                return productRepository.save(product);
            } else {
                throw new IllegalStateException("Not enough space in warehouse.");
            }
        } else {
            throw new IllegalStateException("Warehouse area not initialized.");
        }
    }

    // Remove a product from the warehouse (Check-out)
    public void removeProduct(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            double productSize = product.getSize();

            // Update warehouse available area
            Optional<WarehouseArea> warehouseAreaOpt = warehouseAreaRepository.findById(1L);

            if (warehouseAreaOpt.isPresent()) {
                WarehouseArea warehouseArea = warehouseAreaOpt.get();
                warehouseArea.setAvailableArea(warehouseArea.getAvailableArea() + productSize);
                warehouseAreaRepository.save(warehouseArea);
                productRepository.deleteById(productId);
            } else {
                throw new IllegalStateException("Warehouse area not initialized.");
            }
        } else {
            throw new IllegalStateException("Product not found.");
        }
    }

    // Get the warehouse area information
    public WarehouseArea getWarehouseArea() {
        return warehouseAreaRepository.findById(1L).orElseThrow(() -> new IllegalStateException("Warehouse area not initialized."));
    }

    // Initialize the warehouse area (if not already initialized)
    public void initializeWarehouseArea(double totalArea) {
        Optional<WarehouseArea> warehouseAreaOpt = warehouseAreaRepository.findById(1L);
        if (warehouseAreaOpt.isEmpty()) {
            WarehouseArea warehouseArea = new WarehouseArea(totalArea, totalArea);
            warehouseAreaRepository.save(warehouseArea);
        } else {
            throw new IllegalStateException("Warehouse area is already initialized.");
        }
    }
}
