package com.credito.creditcore.domain.port;

import com.credito.creditcore.domain.model.Persona;

public interface PersonaRepositoryPort {
    Persona guardar(Persona persona);
    Persona consultar(String documento);
    void actualizar(String documento, Persona persona);
    void eliminar(String documento);
} 