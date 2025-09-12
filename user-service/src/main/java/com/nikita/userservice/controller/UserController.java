package com.nikita.userservice.controller;

import com.nikita.commonmodels.DeliveryLocation;
import com.nikita.commonmodels.OrderDto;
import com.nikita.userservice.client.OrderServiceClient;
import com.nikita.userservice.model.dto.UserCreateRequestDto;
import com.nikita.userservice.model.dto.UserDto;
import com.nikita.userservice.model.dto.UserWithAddressesDto;
import com.nikita.userservice.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final SimpMessagingTemplate template;

    private final OrderServiceClient orderServiceClient;

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserCreateRequestDto request) {
        return userServiceImpl.register(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        return userServiceImpl.getUserById(id);
    }


    @GetMapping("/{id}/with-addresses")
    public ResponseEntity<UserWithAddressesDto> getUserByIdWithAddresses(@PathVariable UUID id) {
        return userServiceImpl.getUserByIdWithAddresses(id);
    }

    @GetMapping("/by-phone/{phone}")
    public ResponseEntity<UserDto> getUserByPhoneNumber(@PathVariable String phone) {
        return userServiceImpl.getUserByPhone(phone);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        return userServiceImpl.deleteUser(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID id, @RequestBody UserDto request) {
        return userServiceImpl.updateUser(id, request);
    }

    /// Локация
    @PostMapping("/{userId}/delivery-location")
    public void deliveryLocation(@PathVariable String userId, @RequestBody DeliveryLocation location) {
        template.convertAndSend("/topic/delivery/" + userId, location);
        System.out.println(location);
    }

    /// Cancel Order
    @PutMapping("/{userId}/orders/{orderId}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(
            @PathVariable("userId") UUID userId,
            @PathVariable("orderId") UUID orderId
    ) {
        return orderServiceClient.cancelOrder(orderId, userId);
    }
}