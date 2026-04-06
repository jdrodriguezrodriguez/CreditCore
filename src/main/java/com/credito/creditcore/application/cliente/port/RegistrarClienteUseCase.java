package com.credito.creditcore.application.cliente.port;

import java.math.BigDecimal;

public interface RegistrarClienteUseCase {
    void registrarCliente(BigDecimal salario, Integer idPersona);
}
