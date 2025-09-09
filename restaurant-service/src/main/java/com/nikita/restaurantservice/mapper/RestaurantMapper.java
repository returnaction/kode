package com.nikita.restaurantservice.mapper;

import com.nikita.restaurantservice.model.dto.RestaurantCreateRequestDto;
import com.nikita.restaurantservice.model.dto.RestaurantDto;
import com.nikita.restaurantservice.model.dto.RestaurantWithMenuDto;
import com.nikita.restaurantservice.model.entity.RestaurantEntity;

import java.util.stream.Collectors;

public class RestaurantMapper {
    public static RestaurantEntity toRestaurantEntity(RestaurantCreateRequestDto dto) {
        return RestaurantEntity.builder()
                .name(dto.getName())
                .city(dto.getCity())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .build();
    }

    public static RestaurantDto toRestaurantDto(RestaurantEntity entity) {
        return RestaurantDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .city(entity.getCity())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .build();
    }

    public static RestaurantWithMenuDto toDtoWithMenu(RestaurantEntity entity) {
        return RestaurantWithMenuDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .city(entity.getCity())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .menu(
                        entity.getMenu().stream()
                                .map(MenuMapper::toDto)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
