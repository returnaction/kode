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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public ResponseEntity<MenuDto> createMenu(UUID restaurantId, @Valid MenuDto request) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден"));

        if (menuRepository.existsByRestaurant_IdAndName(restaurantId, request.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Блюдо с таким именем уже существует");
        }

        MenuEntity entity = MenuMapper.toEntity(request);
        entity.setRestaurant(restaurant);
        MenuEntity saved = menuRepository.save(entity);
        MenuDto dto = MenuMapper.toDto(saved);
        dto.setRestaurantId(restaurantId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @Transactional
    public ResponseEntity<MenuDto> updateMenu(UUID restaurantId, UUID menuId, @Valid MenuDto request) {
        MenuEntity menu = menuRepository.findById(menuId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Меню не было найдено"));
        if (!menu.getRestaurant().getId().equals(restaurantId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не был найден");
        }

        menu.setName(request.getName());
        menu.setPrice(request.getPrice());
        menu.setDescription(request.getDescription());

        MenuEntity saved = menuRepository.save(menu);//TODO ну тут явно косяк Если уже Меню с таким именем существует!
        MenuDto dto = MenuMapper.toDto(saved);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<MenuDto> getMenuById(UUID restaurantId, UUID menuId) {
        MenuEntity menu = menuRepository.findById(menuId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Меню не найдено"));

        if (!menu.getRestaurant().getId().equals(restaurantId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Меню не принадлежит данному ресторану");
        }

        MenuDto dto = MenuMapper.toDto(menu);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Void> deleteMenu(UUID restaurantId, UUID menuId) {
        MenuEntity menu = menuRepository.findById(menuId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Меню не найдено"));
        if (!menu.getRestaurant().getId().equals(restaurantId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Меню не принадлежит данному ресторану");
        }

        menuRepository.delete(menu);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
