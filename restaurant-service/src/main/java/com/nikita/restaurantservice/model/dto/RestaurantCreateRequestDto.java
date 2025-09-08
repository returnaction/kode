package com.nikita.restaurantservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantCreateRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;




}
