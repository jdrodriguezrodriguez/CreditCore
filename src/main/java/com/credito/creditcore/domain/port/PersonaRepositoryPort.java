package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Persona;

public interface PersonaRepositoryPort {
    Persona guardar(Persona persona);
    Optional<Persona> consultar(String documento);
    void actualizar(String documento, Persona persona);
    void eliminar(String documento);
    boolean existePorDocumento(String documento);

    Optional<Persona> findById(Integer idPersona);
} 