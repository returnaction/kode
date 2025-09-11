package com.nikita.cardservice.controller;

import com.nikita.cardservice.model.dto.CartDto;
import com.nikita.cardservice.service.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto request){
        return cartService.createCart(request);
    }

    @GetMapping("{cartId}/user/{userId}")
    public ResponseEntity<CartDto> getCartByCartId(@PathVariable UUID cartId, @PathVariable UUID userId){
        return cartService.getCartByCartIdAndUserId(cartId, userId);
    }

    @DeleteMapping("{cartId}/user/{userId}")
    public ResponseEntity<Void> deleteCartByCartId(@PathVariable UUID cartId, @PathVariable UUID userId){
        return cartService.deleteCart(cartId, userId);
    }
}
