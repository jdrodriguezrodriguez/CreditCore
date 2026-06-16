package com.credito.creditcore.application.persona.port.in;

import com.credito.creditcore.domain.model.Person;

public interface RegistrarPersonaUseCase {
    void registrarPersona(Person persona, String password);
}
