package com.credito.creditcore.application.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(
    @NotBlank(message = "La contraseña es obligatoria")
    String password
){
}
