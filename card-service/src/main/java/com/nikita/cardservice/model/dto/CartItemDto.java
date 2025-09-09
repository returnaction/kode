package com.nikita.cardservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {

    private UUID id;
    private UUID menuId;
    private String name;
    private Double price;
    private Integer quantity;
    private UUID cartId;
}
