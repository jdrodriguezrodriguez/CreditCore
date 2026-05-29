package com.credito.creditcore.application.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record UpdateUsuarioDto(
        @NotBlank(message = "El username es obligatorio") String username,

        @NotBlank(message = "La contraseña es obligatoria") String password
    ) {
}
