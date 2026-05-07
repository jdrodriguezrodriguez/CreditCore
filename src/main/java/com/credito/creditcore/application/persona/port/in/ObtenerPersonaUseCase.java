package com.credito.creditcore.application.persona.port.in;

import com.credito.creditcore.domain.model.Persona;

public interface ObtenerPersonaUseCase {
    Persona obtenerPersona(String documento);
}
