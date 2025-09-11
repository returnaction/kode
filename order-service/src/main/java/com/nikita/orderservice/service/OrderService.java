package com.nikita.orderservice.service;

import com.nikita.orderservice.mapper.OrderMapper;
import com.nikita.orderservice.model.Status;
import com.nikita.orderservice.model.dto.OrderDto;
import com.nikita.orderservice.model.dto.OrderItemDto;
import com.nikita.orderservice.model.dto.OrderRequestDto;
import com.nikita.orderservice.model.entity.OrderEntity;
import com.nikita.orderservice.model.entity.OrderItemEntity;
import com.nikita.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public ResponseEntity<OrderDto> createOrder(OrderRequestDto request) {
        if(!checkIfOrderMin300(request))
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Заказа должен быть минимум 300");
        OrderEntity entity = OrderMapper.toEntity(request);
        OrderEntity saved = orderRepository.save(entity);
        OrderDto responseDto = OrderMapper.toResponseDto(saved);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<OrderDto> getOrderById(UUID orderId, UUID userId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Заказа не найден"));
        if (!order.getUserId().equals(userId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Заказ не принадлежит данному пользователю");

        OrderDto orderDto = OrderMapper.toResponseDto(order);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Void> deleteOrder(UUID orderId, UUID userId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Заказ не найден"));
        if (!order.getUserId().equals(userId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Заказ не принадлежит данному пользователю");

        orderRepository.delete(order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Transactional
    public ResponseEntity<OrderDto> updateOrder(UUID orderId, UUID userId, OrderDto request) {
        if (request == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Отсутствует обновленный заказ");

        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Заказ не найден"));
        if (!order.getUserId().equals(userId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Заказ не принадлежит данному пользователю");

        if (request.getAddress() != null)
            order.setAddress(request.getAddress());

        if (request.getAmount() != null)
            order.setAmount(request.getAmount());

        // TODO статус может тут не трогаем у нас отдельный endpoint есть для статуса
        if (request.getStatus() != null)
            order.setStatus(request.getStatus());

        if (request.getItems() != null) {
            order.getItems().clear();

            for (OrderItemDto dto : request.getItems()) {
                OrderItemEntity item = OrderItemEntity.builder()
                        .menuId(dto.getMenuId())
                        .name(dto.getName())
                        .price(dto.getPrice())
                        .quantity(dto.getQuantity())
                        .order(order)
                        .build();
                order.getItems().add(item);
            }
        }
        OrderEntity saved = orderRepository.save(order);
        OrderDto responseDto = OrderMapper.toResponseDto(saved);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<OrderDto> updateOrderStatus(UUID orderId, UUID userId, Status status) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Заказ не найден"));
        if (!order.getUserId().equals(userId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Заказ не принадлежит данному пользователю");

        if (status == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Отсутствует статус");
        order.setStatus(status);
        OrderEntity saved = orderRepository.save(order);
        OrderDto responseDto = OrderMapper.toResponseDto(saved);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    private boolean checkIfOrderMin300(OrderRequestDto request) {
        return request.getAmount() >= 300;
    }
}
