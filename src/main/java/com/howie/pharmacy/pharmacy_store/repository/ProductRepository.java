package com.howie.pharmacy.pharmacy_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howie.pharmacy.pharmacy_store.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String name);
    Product findByName(String name);
    
    // Additional methods can be defined here as needed
}
