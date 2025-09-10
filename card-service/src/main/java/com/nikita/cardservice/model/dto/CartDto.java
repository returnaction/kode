package com.nikita.cardservice.model.dto;

import com.nikita.cardservice.model.entity.CartItemEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    private UUID id;
    private UUID userId;
    private UUID restaurantId;
    private Double totalPrice;
    private List<CartItemDto> items;
}
