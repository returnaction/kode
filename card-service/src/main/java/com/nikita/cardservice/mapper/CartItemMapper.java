package com.nikita.cardservice.mapper;

import com.nikita.cardservice.model.dto.CartItemDto;
import com.nikita.cardservice.model.entity.CartItemEntity;

public class CartItemMapper {
    public static CartItemEntity toEntity(CartItemDto dto) {
        return CartItemEntity.builder()
                .id(dto.getId())
                .menuId(dto.getMenuId())
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }

    public static CartItemDto toDto(CartItemEntity entity) {
        return CartItemDto.builder()
                .id(entity.getId())
                .menuId(entity.getMenuId())
                .name(entity.getName())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .cartId(entity.getCart() != null ? entity.getCart().getId() : null)
                .build();
    }
}
