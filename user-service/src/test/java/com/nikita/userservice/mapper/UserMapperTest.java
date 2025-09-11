package com.nikita.userservice.mapper;

import com.nikita.userservice.model.dto.AddressDto;
import com.nikita.userservice.model.dto.UserCreateRequestDto;
import com.nikita.userservice.model.dto.UserDto;
import com.nikita.userservice.model.dto.UserWithAddressesDto;
import com.nikita.userservice.model.entity.UserAddressEntity;
import com.nikita.userservice.model.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void toUserEntity_mapsAllFields() {
        UserCreateRequestDto dto = new UserCreateRequestDto();
        dto.setPhone("+375296064176");
        dto.setPassword("12345");
        dto.setEmail("nikita@yandex.by");
        dto.setFirstname("Nikita");
        dto.setLastname("Obergan");

        UserEntity entity = UserMapper.toUserEntity(dto);

        assertEquals("+375296064176", entity.getPhone());
        assertEquals("12345", entity.getPassword());
        assertEquals("nikita@yandex.by", entity.getEmail());
        assertEquals("Nikita", entity.getFirstname());
        assertEquals("Obergan", entity.getLastname());
    }

    @Test
    void toUserResponseDto_mapsAllFields() {
        UUID id = UUID.randomUUID();
        UserEntity entity = UserEntity.builder()
                .id(id)
                .phone("+375296064176")
                .email("nikita@yandex.by")
                .firstname("Nikita")
                .lastname("Obergan")
                .build();

        UserDto dto = UserMapper.toUserResponseDto(entity);

        assertEquals(id, dto.getId());
        assertEquals("+375296064176", dto.getPhone());
        assertEquals("nikita@yandex.by", dto.getEmail());
        assertEquals("Nikita", dto.getFirstname());
        assertEquals("Obergan", dto.getLastname());
    }

    @Test
    void toDto_mapsAllFields() {
        UUID addressId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        UserEntity user = UserEntity.builder().id(userId).build();

        UserAddressEntity entity = UserAddressEntity.builder()
                .id(addressId)
                .city("Minsk")
                .street("Lenina")
                .apartment("12")
                .house("5")
                .user(user)
                .build();

        AddressDto dto = AddressMapper.toDto(entity);

        assertEquals(addressId, dto.getId());
        assertEquals("Minsk", dto.getCity());
        assertEquals("Lenina", dto.getStreet());
        assertEquals("12", dto.getApartment());
        assertEquals("5", dto.getHouse());
        assertEquals(userId, dto.getUserId());
    }

}