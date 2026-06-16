package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Prestamo;

public interface LoanRepositoryPort {
    Optional<Prestamo> obtenerPorIdCliente(Integer idCliente);
    Optional<Prestamo> obtenerPorId(Integer idPrestamo);
    void guardar(Prestamo prestamo, Customer cliente);
    void actualizar(Integer idCliente, Prestamo prestamo);
} 
