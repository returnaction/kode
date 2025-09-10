package com.nikita.deliveryservice.model.dto.courier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourierRequestDto {
    //  TODO добавь валидвацию
    private String name;
    private String email;
    private String phone;
}
