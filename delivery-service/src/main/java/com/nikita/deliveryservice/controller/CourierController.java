package com.nikita.deliveryservice.controller;

import com.nikita.deliveryservice.model.dto.CourierDto;
import com.nikita.deliveryservice.model.dto.CourierRequestDto;
import com.nikita.deliveryservice.model.entity.courier.CourierEntity;
import com.nikita.deliveryservice.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/courier")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @PostMapping
    public ResponseEntity<CourierDto> createCourier(@RequestBody CourierRequestDto request) {
        return courierService.createCourier(request);
    }

    @GetMapping("/{courierId}")
    public ResponseEntity<CourierDto> getCourier(@PathVariable UUID courierId) {
        return courierService.findById(courierId);
    }

    @DeleteMapping("/{courierId}")
    public ResponseEntity<Void> deleteCourier(@PathVariable UUID courierId) {
        return courierService.deleteCourier(courierId);
    }

    @PutMapping("/{courierId}")
    public ResponseEntity<CourierDto> updateCourier(@PathVariable UUID courierId, @RequestBody CourierDto request) {
        return courierService.updateCourier(courierId, request);
    }
}
