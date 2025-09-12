package com.nikita.orderservice.controller;

import com.nikita.orderservice.model.Status;
import com.nikita.orderservice.model.dto.OrderRequestDto;
import com.nikita.orderservice.model.dto.OrderDto;
import com.nikita.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //TODO MUST_DO Когда создаём заказ можно создавать сущность Delivery что бы заказ был создан но не назначен еще курьеру
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequestDto request){
        return orderService.createOrder(request);
    }

    @GetMapping("/{orderId}/user/{userId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") UUID orderId, @PathVariable("userId") UUID userId){
        return orderService.getOrderById(orderId, userId);
    }

    @DeleteMapping("/{orderId}/user/{userId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") UUID orderId, @PathVariable("userId") UUID userId){
        return orderService.deleteOrder(orderId, userId);
    }

    @PutMapping("/{orderId}/user/{userId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("orderId") UUID orderId, @PathVariable("userId") UUID userId, @RequestBody OrderDto request){
        return orderService.updateOrder(orderId, userId, request);
    }

    @PutMapping("/{orderId}/user/{userId}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable("orderId") UUID orderId, @PathVariable("userId") UUID userId, @RequestParam Status status){
        return orderService.updateOrderStatus(orderId, userId, status);
    }

    @PutMapping("/{orderId}/user/{userId}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable("orderId") UUID orderId, @PathVariable("userId") UUID userId){
        System.out.println("order-service контролер мать его!!!");
        return orderService.cancelOrder(orderId, userId);
    }
}