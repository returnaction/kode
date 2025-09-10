package com.nikita.deliveryservice.service;

import com.nikita.deliveryservice.mapper.DeliveryMapper;
import com.nikita.deliveryservice.model.DeliveryStatus;
import com.nikita.deliveryservice.model.dto.delivery.DeliveryDto;
import com.nikita.deliveryservice.model.entity.courier.CourierEntity;
import com.nikita.deliveryservice.model.entity.delivery.DeliveryEntity;
import com.nikita.deliveryservice.repository.CourierRepository;
import com.nikita.deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final CourierRepository courierRepository;

    @Transactional
    public ResponseEntity<DeliveryDto> addDelivery(UUID orderId) {
        DeliveryEntity entity = DeliveryEntity.builder()
                .orderId(orderId)
                .status(DeliveryStatus.PENDING)
                .build();
        DeliveryEntity saved = deliveryRepository.save(entity);
        DeliveryDto dto = DeliveryMapper.toDto(saved);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<DeliveryDto> getDelivery(UUID deliveryId) {
        DeliveryEntity entity = deliveryRepository.findById(deliveryId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Доставка не найдена"));
        DeliveryDto dto = DeliveryMapper.toDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //TODO так тут надо переделать так что у нас у курьеров должны быть Boolean in Delivery или Not in Delivery
    // вообще лучше сделать что бы у ресторана были тоже координаты и мы смотрели какие курьеры ближе к ресторану
    // тех и выбирали но сейчас для простоты выберем какого нибудь конкретного
    @Transactional
    public ResponseEntity<DeliveryDto> assignCourier(UUID deliveryId, UUID courierId) {
        DeliveryEntity delivery = deliveryRepository.findById(deliveryId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Доставка не найдена"));
        CourierEntity courier = courierRepository.findById(courierId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Курьер не найден"));
        delivery.setCourier(courier);
        delivery.setStatus(DeliveryStatus.IN_PROGRESS);
        DeliveryEntity saved = deliveryRepository.save(delivery);
        DeliveryDto dto = DeliveryMapper.toDto(saved);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<DeliveryDto> updateDeliveryStatus(UUID deliveryId, UUID courierId, DeliveryStatus status) {
        DeliveryEntity entity = deliveryRepository.findById(deliveryId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Заказ не найдет"));
        if (!entity.getCourier().getCourierId().equals(courierId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Доставка не принадлежит данному курьеру");

        entity.setStatus(status);
        DeliveryEntity saved = deliveryRepository.save(entity);
        DeliveryDto dto = DeliveryMapper.toDto(saved);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



}
