package com.credito.creditcore.application.dto.persona;

import com.credito.creditcore.application.dto.usuario.UsuarioDto;

public record RegistrarPersonaCommand(
                PersonaDto persona,
                UsuarioDto usuario) {
}
