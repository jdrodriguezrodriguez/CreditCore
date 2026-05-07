package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.domain.port.ClienteRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.ClienteMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.persistence.ClienteRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteRepositoryJpa clienteRepositoryJpa;

    public ClienteRepositoryAdapter(ClienteRepositoryJpa clienteRepositoryJpa) {
        this.clienteRepositoryJpa = clienteRepositoryJpa;
    }

    @Override
    public void guardar(Cliente cliente, Persona persona) {

        PersonaEntity personaEntity = PersonaMapperOut.toEntity(persona);

        clienteRepositoryJpa.save(ClienteMapperOut.crearEntidad(cliente, personaEntity));
    }

    @Override
    public void actualizar(Integer idCliente, Cliente cliente) {
        ClienteEntity clienteEntity = clienteRepositoryJpa.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException());

        clienteRepositoryJpa.save(ClienteMapperOut.actualizarEntity(clienteEntity, cliente));
    }

    @Override
    public Optional<Cliente> obtenerPorId(Integer idCliente) {
        return clienteRepositoryJpa.findById(idCliente).map(cliente -> {
            Persona persona = PersonaMapperOut.toDomain(cliente.getPersona());
            return ClienteMapperOut.toDomain(cliente, persona);
        });
    }

    @Override
    public Optional<Cliente> obtenerPorIdPersona(Integer idPersona) {
        return clienteRepositoryJpa.findByPersona_idPersona(idPersona).map(cliente -> {
            Persona persona = PersonaMapperOut.toDomain(cliente.getPersona());
            return ClienteMapperOut.toDomain(cliente, persona);
        });
    }
}
