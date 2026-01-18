package com.howie.pharmacy.pharmacy_store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.howie.pharmacy.pharmacy_store.dto.order.OrderCreateDto;
import com.howie.pharmacy.pharmacy_store.dto.order.OrderDto;
import com.howie.pharmacy.pharmacy_store.dto.order.OrderResponseDto;
import com.howie.pharmacy.pharmacy_store.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<OrderResponseDto> getAllOrders() {
        return new ResponseEntity(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        OrderDto newOrder = orderService.createOrder(orderCreateDto);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
}
