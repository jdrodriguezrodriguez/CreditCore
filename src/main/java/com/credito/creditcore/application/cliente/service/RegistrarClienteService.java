package com.credito.creditcore.application.cliente.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.cliente.port.RegistrarClienteUseCase;
import com.credito.creditcore.domain.excepcion.PersonaNoEncontradaException;
import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.domain.port.ClienteRepositoryPort;
import com.credito.creditcore.domain.port.PersonaRepositoryPort;

@Service
public class RegistrarClienteService implements RegistrarClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final PersonaRepositoryPort personaRepositoryPort;

    public RegistrarClienteService(ClienteRepositoryPort clienteRepositoryPort,
            PersonaRepositoryPort personaRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.personaRepositoryPort = personaRepositoryPort;
    }

    @Override
    public void registrarCliente(BigDecimal salario, Integer idPersona) {

        Optional<Persona> persona = personaRepositoryPort.findById(idPersona);

        if (!persona.isPresent()) {
            throw new PersonaNoEncontradaException("Persona no encontrada con el IdPersona: " + idPersona);
        }

        Integer historial = 0;

        Cliente ClienteNuevo = Cliente.crearCliente(salario, historial, persona.get());

        clienteRepositoryPort.guardar(ClienteNuevo, persona.get());
    }

    /*
     * Historial de pagos → 40% (400 pts)
     * Capacidad de pago → 30% (300 pts)
     * Endeudamiento actual → 20% (200 pts)
     * Antigüedad como cliente → 10% (100 pts)
     */
}
