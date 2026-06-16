package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Customer;

public interface CustomerRepositoryPort {
    void save(Customer cliente);
    void update(Integer idCliente, Customer cliente);
    Optional<Customer> findById(Integer idCliente);
    Optional<Customer> obtenerPorIdPersona(Integer idPersona);
}