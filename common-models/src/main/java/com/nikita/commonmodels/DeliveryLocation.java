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
public class DeliveryLocation {
    private UUID deliveryId;
    private UUID userId;
    private double coordinateX;
    private double coordinateY;
}
