package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Cliente;

public interface ClienteRepositoryPort{

    void guardar(Cliente cliente, String documento);
    void actualizar(Integer idCliente, Cliente cliente);
    Optional<Cliente> consultar(Integer idCliente);
}