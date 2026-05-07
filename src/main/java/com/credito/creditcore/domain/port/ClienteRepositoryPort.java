package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.Persona;

public interface ClienteRepositoryPort {
    void guardar(Cliente cliente, Persona persona);
    void actualizar(Integer idCliente, Cliente cliente);
    Optional<Cliente> obtenerPorId(Integer idCliente);
    Optional<Cliente> obtenerPorIdPersona(Integer idPersona);
}