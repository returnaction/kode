package com.nikita.deliveryservice.service.client;

import com.nikita.deliveryservice.model.dto.delivery.DeliveryLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserServiceClient {

    private final RestTemplate restTemplate;

    public void sendLocation(DeliveryLocation location) {
        String url = "http://localhost:8080/users/{userId}/delivery-location";
        restTemplate.postForObject(url, location, Void.class, location.getUserId());
    }
}
