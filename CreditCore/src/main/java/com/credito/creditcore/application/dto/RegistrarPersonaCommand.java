package com.credito.creditcore.application.dto;

public record RegistrarPersonaCommand(
                PersonaDto persona,
                UsuarioDto usuario) {
}
