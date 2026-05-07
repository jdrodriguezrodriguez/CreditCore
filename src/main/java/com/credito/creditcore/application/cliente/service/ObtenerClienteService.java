package com.credito.creditcore.application.cliente.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.cliente.port.ObtenerClienteUseCase;
import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.port.ClienteRepositoryPort;

@Service
public class ObtenerClienteService implements ObtenerClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ObtenerClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public Cliente obtenerCliente(Integer idCliente) {
        return clienteRepositoryPort.obtenerPorId(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("No hay clientes registrados con el idCliente: " + idCliente));
    }

}
