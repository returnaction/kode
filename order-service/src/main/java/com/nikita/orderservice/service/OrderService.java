package com.nikita.orderservice.service;

import com.nikita.orderservice.mapper.OrderMapper;
import com.nikita.orderservice.model.dto.OrderRequestDto;
import com.nikita.orderservice.model.dto.OrderResponseDto;
import com.nikita.orderservice.model.entity.OrderEntity;
import com.nikita.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public ResponseEntity<OrderResponseDto> createOrder(OrderRequestDto request) {
        OrderEntity entity = OrderMapper.toEntity(request);
        OrderEntity saved = orderRepository.save(entity);
        OrderResponseDto responseDto = OrderMapper.toResponseDto(saved);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
