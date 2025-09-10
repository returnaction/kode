package com.nikita.cardservice.mapper;

import com.nikita.cardservice.model.dto.CartDto;
import com.nikita.cardservice.model.entity.CartEntity;

import java.util.stream.Collectors;

public class CartMapper {
    public static CartEntity toEntityWithItems(CartDto dto) {
        return CartEntity.builder()
                .id(dto.getId() == null ? null : dto.getId())
                .userId(dto.getUserId())
                .restaurantId(dto.getRestaurantId())
                .totalPrice(dto.getTotalPrice() == null ? 0.0 : dto.getTotalPrice())
                .items(
                        dto.getItems().stream()
                                .map(CartItemMapper::toEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public static CartDto toDto(CartEntity entity) {
        return CartDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .restaurantId(entity.getRestaurantId())
                .totalPrice(entity.getTotalPrice())
                .build();
    }

    public static CartDto toDtoWithItems(CartEntity entity) {
        return CartDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .restaurantId(entity.getRestaurantId())
                .totalPrice(entity.getTotalPrice())
                .items(
                        entity.getItems().stream()
                                .map(CartItemMapper::toDto)
                                .collect(Collectors.toList())
                ).build();
    }
}
