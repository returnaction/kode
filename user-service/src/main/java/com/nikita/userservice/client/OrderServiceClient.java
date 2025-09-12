package com.nikita.userservice.client;

import com.nikita.commonmodels.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "user-service", url = "http://localhost:8083")
public interface OrderServiceClient {

    @PutMapping("/orders/{orderId}/user/{userId}/cancel")
    ResponseEntity<OrderDto> cancelOrder(
            @PathVariable("orderId") UUID orderId,
            @PathVariable("userId") UUID userId
    );
}
