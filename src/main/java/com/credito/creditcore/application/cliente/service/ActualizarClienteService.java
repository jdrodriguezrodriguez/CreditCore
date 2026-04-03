package com.credito.creditcore.application.cliente.service;

import java.util.Optional;

import com.credito.creditcore.application.cliente.port.ActualizarClienteUseCase;
import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.port.ClienteRepositoryPort;

public class ActualizarClienteService implements ActualizarClienteUseCase {

    private ClienteRepositoryPort clienteRepositoryPort;

    public ActualizarClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public void actualizarCliente(Integer idCliente, Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepositoryPort.consultar(idCliente);

        clienteExistente.ifPresent(c -> {
            clienteRepositoryPort.actualizar(idCliente, cliente);
        });

    }

}
