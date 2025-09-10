package com.nikita.deliveryservice.model.dto.delivery;

import com.nikita.deliveryservice.model.DeliveryStatus;
import com.nikita.deliveryservice.model.dto.courier.CourierDto;
import com.nikita.deliveryservice.model.entity.courier.CourierEntity;
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
