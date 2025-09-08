package com.nikita.restaurantservice.service.impl;

import com.nikita.restaurantservice.mapper.RestaurantMapper;
import com.nikita.restaurantservice.model.dto.RestaurantCreateRequestDto;
import com.nikita.restaurantservice.model.dto.RestaurantDto;
import com.nikita.restaurantservice.model.entity.RestaurantEntity;
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
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public ResponseEntity<RestaurantDto> createRestaurant(@Valid RestaurantCreateRequestDto request) {
        if (restaurantRepository.existsByName(request.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ресторан с таким именем уже существует");
        }
        RestaurantEntity restaurantEntity = RestaurantMapper.toRestaurantEntity(request);
        RestaurantEntity saved = restaurantRepository.save(restaurantEntity);
        RestaurantDto restaurantDto = RestaurantMapper.toRestaurantDto(saved);
        return new ResponseEntity<>(restaurantDto, HttpStatus.CREATED);
    }

    public ResponseEntity<RestaurantDto> getRestaurantById(UUID id) {
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден"));

        RestaurantDto restaurantDto = RestaurantMapper.toRestaurantDto(restaurant);
        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
    }

    public ResponseEntity<RestaurantDto> getRestaurantByName(String name) {
        RestaurantEntity restaurant = restaurantRepository.findByName(name).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден"));

        RestaurantDto restaurantDto = RestaurantMapper.toRestaurantDto(restaurant);
        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteRestaurant(UUID id) {
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден"));
        restaurantRepository.delete(restaurant);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<RestaurantDto> updateRestaurant(UUID id, RestaurantDto request){
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден"));

        restaurant.setName(request.getName());
        restaurant.setCity(request.getCity());
        restaurant.setAddress(request.getAddress());
        restaurant.setPhone(request.getPhone());

        RestaurantEntity saved = restaurantRepository.save(restaurant);
        RestaurantDto restaurantDto = RestaurantMapper.toRestaurantDto(saved);
        return new ResponseEntity<>(restaurantDto, HttpStatus.OK); //TODO перепроверить правильный ли ля update статус код
    }
}
