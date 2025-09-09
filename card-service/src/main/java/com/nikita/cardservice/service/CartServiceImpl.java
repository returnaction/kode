package com.nikita.cardservice.service;

import com.nikita.cardservice.model.dto.CartDto;
import com.nikita.cardservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl {

    private final CartRepository cartRepository;

    public ResponseEntity<CartDto> createCart(CartDto request) {

    }
}
