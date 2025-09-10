package com.nikita.orderservice.mapper;

import com.nikita.orderservice.model.dto.OrderItemRequestDto;
import com.nikita.orderservice.model.dto.OrderItemDto;
import com.nikita.orderservice.model.entity.OrderEntity;
import com.nikita.orderservice.model.entity.OrderItemEntity;

public class OrderItemMapper {
    public static OrderItemEntity toEntity(OrderItemRequestDto dto, OrderEntity orderDto) {
        return OrderItemEntity.builder()
                .menuId(dto.getMenuId())
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .order(orderDto)
                .build();
    }

    public static OrderItemDto toDtoResponse(OrderItemEntity entity){
        return OrderItemDto.builder()
                .orderItemId(entity.getOrderItemId())
                .menuId(entity.getMenuId())
                .name(entity.getName())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .build();
    }
}
