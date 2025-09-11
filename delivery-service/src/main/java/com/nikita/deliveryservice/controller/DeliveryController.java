package com.nikita.deliveryservice.controller;

import com.nikita.deliveryservice.model.DeliveryStatus;
import com.nikita.deliveryservice.model.dto.delivery.DeliveryDto;
import com.nikita.deliveryservice.model.dto.delivery.DeliveryLocation;
import com.nikita.deliveryservice.model.entity.delivery.DeliveryEntity;
import com.nikita.deliveryservice.repository.DeliveryRepository;
import com.nikita.deliveryservice.service.DeliveryService;
import com.nikita.deliveryservice.service.client.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    private final UserServiceClient userServiceClient;
    private final DeliveryRepository deliveryRepository;

    @PostMapping
    public ResponseEntity<DeliveryDto> addDelivery(@RequestParam UUID orderId){
        return deliveryService.addDelivery(orderId);
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryDto> getDelivery(@PathVariable UUID deliveryId){
        return deliveryService.getDelivery(deliveryId);
    }

    @PutMapping("/{deliveryId}/courier/{courierId}/assign")
    public ResponseEntity<DeliveryDto> assignCourier(@PathVariable UUID deliveryId, @PathVariable UUID courierId){
       return deliveryService.assignCourier(deliveryId, courierId);
    }

    @PutMapping("/{deliveryId}/courier/{courierId}")
    public ResponseEntity<DeliveryDto> updateDeliveryStatus(
            @PathVariable UUID deliveryId,
            @PathVariable UUID courierId,
            @RequestParam DeliveryStatus status){
        return deliveryService.updateDeliveryStatus(deliveryId, courierId, status);
    }

    //TODO CompleteOrder
    //TODO подумать как отправить координаты с помощью Scheduler может? Такую заглушку

    @PostMapping("/{deliveryId}/location")
    public ResponseEntity<Void> updateLocation(@PathVariable UUID deliveryId, @RequestBody DeliveryLocation location) {
        return deliveryService.updateLocation(deliveryId, location);
    }
}
