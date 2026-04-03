package com.credito.creditcore.application.cliente.service;

import com.credito.creditcore.application.cliente.port.ObtenerClienteUseCase;
import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.port.ClienteRepositoryPort;

public class ObtenerClienteService implements ObtenerClienteUseCase {

    private ClienteRepositoryPort clienteRepositoryPort;

    public ObtenerClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public Cliente obtenerCliente(Integer idCliente) {
        return clienteRepositoryPort.consultar(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("No hay clientes registrados con ese ID"));
    }

}
