package com.nikita.restaurantservice.service.impl;

import com.nikita.restaurantservice.mapper.MenuMapper;
import com.nikita.restaurantservice.model.dto.MenuDto;
import com.nikita.restaurantservice.model.entity.MenuEntity;
import com.nikita.restaurantservice.model.entity.RestaurantEntity;
import com.nikita.restaurantservice.repository.MenuRepository;
import com.nikita.restaurantservice.repository.RestaurantRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public ResponseEntity<MenuDto> createMenu(UUID restaurantId, @Valid MenuDto request) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден"));

        // 2) Уникальность имени в рамках ресторана
        if (menuRepository.existsByRestaurant_IdAndName(restaurantId, request.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Блюдо с таким именем уже существует");
        }

        MenuEntity entity = MenuMapper.toMenuEntity(request);
        entity.setRestaurant(restaurant);
        MenuEntity saved = menuRepository.save(entity);
        MenuDto dto = MenuMapper.toMenuDto(saved);
        dto.setRestaurantId(restaurantId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
