package com.credito.creditcore.application.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDto(
        @NotBlank(message = "The username is required.") String username,

        @NotBlank(message = "The password is required.") String password
    ) {
}
