package com.credito.creditcore.application.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(
    @NotBlank(message = "La contraseña es obligatoria")
    String password
){
}
