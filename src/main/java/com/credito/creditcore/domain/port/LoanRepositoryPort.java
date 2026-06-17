package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Loan;

public interface LoanRepositoryPort {
    Optional<Loan> obtenerPorIdCliente(Integer idCliente);
    Optional<Loan> obtenerPorId(Integer idPrestamo);
    void guardar(Loan prestamo, Customer cliente);
    void actualizar(Integer idCliente, Loan prestamo);
} 
