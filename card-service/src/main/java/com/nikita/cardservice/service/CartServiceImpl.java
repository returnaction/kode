package com.nikita.cardservice.service;

import com.nikita.cardservice.mapper.CartMapper;
import com.nikita.cardservice.model.dto.CartDto;
import com.nikita.cardservice.model.entity.CartEntity;
import com.nikita.cardservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl {

    private final CartRepository cartRepository;

    public ResponseEntity<CartDto> createCart(CartDto request) {
        CartEntity entity = CartMapper.toEntityWithItems(request);
        entity.getItems().forEach(item -> item.setCart(entity)); // TODO исправить может в мапере
        CartEntity saved = cartRepository.save(entity);
        CartDto dto = CartMapper.toDtoWithItems(saved);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    public ResponseEntity<CartDto> getCartByCartIdAndUserId(UUID cartId, UUID userId) {
        CartEntity cart = cartRepository.findById(cartId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Корзина не найдена"));
        if (!cart.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Корзина не принадлежит данному пользователю");
        }

        CartDto dtoWithItems = CartMapper.toDtoWithItems(cart);
        return new ResponseEntity<>(dtoWithItems, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteCart(UUID cartId, UUID userId) {
        CartEntity cart = cartRepository.findById(cartId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Корзина не найдена"));
        if (!cart.getUserId().equals(userId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Корзина не принадлежит данному пользователю");

        cartRepository.delete(cart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
