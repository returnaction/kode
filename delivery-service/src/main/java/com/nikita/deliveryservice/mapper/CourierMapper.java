package com.nikita.deliveryservice.mapper;

import com.nikita.deliveryservice.model.dto.courier.CourierDto;
import com.nikita.deliveryservice.model.dto.courier.CourierRequestDto;
import com.nikita.deliveryservice.model.entity.courier.CourierEntity;

public class CourierMapper {
    public static CourierEntity toEntity(CourierRequestDto dto) {
        return CourierEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }

    public static CourierDto toDto(CourierEntity entity){
        return CourierDto.builder()
                .courierId(entity.getCourierId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
    }
}
