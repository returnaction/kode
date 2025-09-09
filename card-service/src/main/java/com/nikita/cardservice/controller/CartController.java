package com.nikita.cardservice.controller;

import com.nikita.cardservice.model.dto.CartDto;
import com.nikita.cardservice.service.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto request){
        return cartService.createCart(request);
    }
}
