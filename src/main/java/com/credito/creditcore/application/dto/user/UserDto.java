package com.credito.creditcore.application.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserDto(
    @NotBlank(message = "The password is required.")
    String password
){
}
