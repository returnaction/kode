package com.nikita.userservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDto {
    @NotBlank
    private String phone;
    @NotBlank
    private String password;
    private String email;
    private String firstname;
    private String lastname;

}
