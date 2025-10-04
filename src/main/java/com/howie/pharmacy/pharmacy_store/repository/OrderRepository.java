package com.howie.pharmacy.pharmacy_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howie.pharmacy.pharmacy_store.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
