package com.credito.creditcore.domain.port;

import com.credito.creditcore.domain.model.Persona;

public interface PersonaRepositoryPort {
    Persona guardar(Persona persona);
} 