package com.nikita.orderservice.client;


import com.nikita.commonmodels.DeliveryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "delivery-service", url = "http://localhost:8085/delivery")
public interface DeliveryServiceClient {

    @PutMapping("/{deliveryId}/cancel")
     ResponseEntity<Boolean> cancelDelivery(@PathVariable("deliveryId") UUID deliveryId);

    @GetMapping("/order/{orderId}")
    ResponseEntity<DeliveryDto> getDeliveryByOrderId(@PathVariable("orderId") UUID orderId);
}
