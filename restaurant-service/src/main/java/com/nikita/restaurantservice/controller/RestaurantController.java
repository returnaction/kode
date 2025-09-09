package com.nikita.restaurantservice.controller;

import com.nikita.restaurantservice.model.dto.MenuDto;
import com.nikita.restaurantservice.model.dto.RestaurantCreateRequestDto;
import com.nikita.restaurantservice.model.dto.RestaurantDto;
import com.nikita.restaurantservice.model.dto.RestaurantWithMenuDto;
import com.nikita.restaurantservice.repository.MenuRepository;
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

    @GetMapping("{restaurant_id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable UUID restaurant_id) {
        return restaurantService.getRestaurantById(restaurant_id);
    }

    @GetMapping("{name}/name")
    public ResponseEntity<RestaurantDto> getRestaurantByName(@PathVariable String name) {
        return restaurantService.getRestaurantByName(name);
    }

    // TODO getAlLRestaurants

    @DeleteMapping("{menu_id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID menu_id) {
        return restaurantService.deleteRestaurant(menu_id);
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

    @GetMapping("/{restaurant_id}/with-menu")
    public ResponseEntity<RestaurantWithMenuDto> getRestaurantByIdWithMenu(@PathVariable UUID restaurant_id) {
        return restaurantService.getRestaurantWithMenu(restaurant_id);
    }

    @GetMapping("/{restaurant_id}/menu/{menu_id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable UUID restaurant_id, @PathVariable UUID menu_id) {
        return menuService.getMenuById(restaurant_id, menu_id);
    }

    @PutMapping("/{restaurant_id}/menu/{menu_id}")
    public ResponseEntity<MenuDto> updateMenu(
            @PathVariable UUID restaurant_id,
            @PathVariable UUID menu_id,
            @RequestBody @Valid MenuDto request) {
        return menuService.updateMenu(restaurant_id, menu_id, request);
    }

    @DeleteMapping("/{restaurant_id}/menu/{menu_id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable UUID restaurant_id, @PathVariable UUID menu_id) {
        return menuService.deleteMenu(restaurant_id, menu_id);
    }


}
