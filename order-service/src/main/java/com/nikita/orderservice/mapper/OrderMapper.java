package com.nikita.orderservice.mapper;

import com.nikita.orderservice.model.Status;
import com.nikita.orderservice.model.dto.OrderRequestDto;
import com.nikita.orderservice.model.dto.OrderResponseDto;
import com.nikita.orderservice.model.entity.OrderEntity;
import com.nikita.orderservice.model.entity.OrderItemEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderEntity toEntity(OrderRequestDto dto) {
        OrderEntity order = OrderEntity.builder()
                .userId(dto.getUserId())
                .address(dto.getAddress())
                .amount(dto.getAmount())
                .status(Status.NEW)
                .build();

        List<OrderItemEntity> items = dto.getItems().stream()
                .map(itemDto -> OrderItemMapper.toEntity(itemDto, order))
                .collect(Collectors.toList());

        order.setItems(items);
        return order;
    }

    public static OrderResponseDto toResponseDto(OrderEntity entity) {
        return OrderResponseDto.builder()
                .orderId(entity.getOrderId())
                .userId(entity.getUserId())
                .address(entity.getAddress())
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .items(
                        entity.getItems().stream()
                                .map(OrderItemMapper::toDtoResponse)
                                .collect(Collectors.toList())
                ).build();
    }
}
