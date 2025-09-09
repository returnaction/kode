package com.nikita.restaurantservice.mapper;

import com.nikita.restaurantservice.model.dto.MenuDto;
import com.nikita.restaurantservice.model.entity.MenuEntity;

public class MenuMapper {
    public static MenuDto toDto(MenuEntity menu) {
       return MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .restaurantId(menu.getRestaurant().getId())
                .build();
    }

    public static MenuEntity toEntity(MenuDto menuDto) {
        return MenuEntity.builder()
                .name(menuDto.getName())
                .price(menuDto.getPrice())
                .description(menuDto.getDescription())
                .build();

    }
}
