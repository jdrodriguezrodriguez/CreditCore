package com.credito.creditcore.application.cliente.port;

import com.credito.creditcore.domain.model.Cliente;

public interface ActualizarClienteUseCase {
    void actualizarCliente(Integer idCliente, Cliente cliente);
}
