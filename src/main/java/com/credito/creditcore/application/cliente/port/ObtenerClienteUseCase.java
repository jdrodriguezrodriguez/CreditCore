package com.credito.creditcore.application.cliente.port;

import com.credito.creditcore.domain.model.Cliente;

public interface ObtenerClienteUseCase {
    Cliente obtenerCliente(Integer idPersona);
}