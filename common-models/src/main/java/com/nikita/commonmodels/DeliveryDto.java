package com.nikita.commonmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryDto {

    private UUID deliveryId;
    private UUID orderId;
    private CourierDto courier;
    private DeliveryStatus status;
    private Double coordinateX;
    private Double coordinateY;
}
