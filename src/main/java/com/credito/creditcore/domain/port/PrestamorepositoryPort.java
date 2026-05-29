package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.Prestamo;

public interface PrestamorepositoryPort {
    Optional<Prestamo> obtenerPorIdCliente(Integer idCliente);
    Optional<Prestamo> obtenerPorId(Integer idPrestamo);
    void guardar(Prestamo prestamo, Cliente cliente);
    void actualizar(Integer idCliente, Prestamo prestamo);
} 
