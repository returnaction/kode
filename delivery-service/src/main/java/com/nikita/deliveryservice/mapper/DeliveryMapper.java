package com.nikita.deliveryservice.mapper;

import com.nikita.deliveryservice.model.dto.delivery.DeliveryDto;
import com.nikita.deliveryservice.model.entity.delivery.DeliveryEntity;

public class DeliveryMapper {
    public static DeliveryDto toDto(DeliveryEntity entity) {
        return DeliveryDto.builder()
                .deliveryId(entity.getDeliveryId())
                .orderId(entity.getOrderId())
                .courier(CourierMapper.toDto(entity.getCourier()))
                .status(entity.getStatus())
                .coordinateX(entity.getCoordinateX())
                .coordinateY(entity.getCoordinateY())
                .build();
    }
}
