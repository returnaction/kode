package com.nikita.restaurantservice.controller;

import com.nikita.restaurantservice.model.dto.MenuDto;
import com.nikita.restaurantservice.model.dto.RestaurantCreateRequestDto;
import com.nikita.restaurantservice.model.dto.RestaurantDto;
import com.nikita.restaurantservice.service.impl.MenuService;
import com.nikita.restaurantservice.service.impl.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MenuService menuService;

    /// Restaurant

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody @Valid RestaurantCreateRequestDto request) {
        return restaurantService.createRestaurant(request);
    }

    @GetMapping("{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("{name}/name")
    public ResponseEntity<RestaurantDto> getRestaurantByName(@PathVariable String name) {
        return restaurantService.getRestaurantByName(name);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
        return restaurantService.deleteRestaurant(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable UUID id, @RequestBody @Valid RestaurantDto request) {
        return restaurantService.updateRestaurant(id, request);
    }

    /// Menu
    @PostMapping("/{restaurant_id}/menu")
    public ResponseEntity<MenuDto> createMenu(@PathVariable UUID restaurant_id, @RequestBody @Valid MenuDto request) {
        return menuService.createMenu(restaurant_id, request);
    }
}
