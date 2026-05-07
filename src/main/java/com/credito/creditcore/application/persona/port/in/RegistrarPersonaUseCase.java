package com.credito.creditcore.application.persona.port.in;

import com.credito.creditcore.domain.model.Persona;

public interface RegistrarPersonaUseCase {
    void registrarPersona(Persona persona, String password);
}
