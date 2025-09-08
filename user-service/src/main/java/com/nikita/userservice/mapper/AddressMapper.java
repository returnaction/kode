package com.nikita.userservice.mapper;

import com.nikita.userservice.model.dto.AddressDto;
import com.nikita.userservice.model.entity.UserAddressEntity;

public class AddressMapper {
    public static AddressDto toDto(UserAddressEntity entity){
        return AddressDto.builder()
                .id(entity.getId())
                .city(entity.getCity())
                .street(entity.getStreet())
                .house(entity.getHouse())
                .apartment(entity.getApartment())
                .userId(entity.getUser().getId())
                .build();
    }
}
