package com.nikita.restaurantservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantWithMenuDto {
    private UUID id;
    private String name;
    private String city;
    private String address;
    private String phone;
    List<MenuDto> menu;
}
