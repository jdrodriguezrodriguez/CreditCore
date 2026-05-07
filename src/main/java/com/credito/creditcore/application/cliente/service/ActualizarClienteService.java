package com.credito.creditcore.application.cliente.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.cliente.port.ActualizarClienteUseCase;
import com.credito.creditcore.domain.excepcion.PersonaNoEncontradaException;
import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.port.ClienteRepositoryPort;

@Service
public class ActualizarClienteService implements ActualizarClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ActualizarClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public void actualizarCliente(Integer idCliente, Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepositoryPort.obtenerPorId(idCliente);

        clienteExistente.ifPresentOrElse(c -> {
            clienteRepositoryPort.actualizar(idCliente, cliente);
        },
            () -> new PersonaNoEncontradaException("No se encontro al cliente con el idCliente: " + idCliente));
    }

}
