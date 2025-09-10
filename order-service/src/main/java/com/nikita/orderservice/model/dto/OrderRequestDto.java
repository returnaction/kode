package com.nikita.orderservice.model.dto;

import com.nikita.orderservice.model.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {
    private UUID userId;
    private String address;
    private Double amount;
    private List<OrderItemDto> items;
}
