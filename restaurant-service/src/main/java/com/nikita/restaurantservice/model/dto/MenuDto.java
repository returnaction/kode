package com.nikita.restaurantservice.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MenuDto {
    private UUID id;
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    private String description;
    private UUID restaurantId;
}
