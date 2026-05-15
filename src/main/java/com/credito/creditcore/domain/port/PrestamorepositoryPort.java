package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Prestamo;

public interface PrestamorepositoryPort {
    Optional<Prestamo> obtenerPorIdPersona(Integer idPersona);
    void guardar(Prestamo prestamo);
} 
