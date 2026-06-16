package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.ClienteMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.persistence.ClienteRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ClienteRepositoryAdapter implements CustomerRepositoryPort {

    private final ClienteRepositoryJpa clienteRepositoryJpa;

    public ClienteRepositoryAdapter(ClienteRepositoryJpa clienteRepositoryJpa) {
        this.clienteRepositoryJpa = clienteRepositoryJpa;
    }

    @Override
    public void save(Customer cliente) {

        PersonaEntity personaEntity = PersonaMapperOut.toEntity(cliente.getPersona());

        clienteRepositoryJpa.save(ClienteMapperOut.crearEntidad(cliente, personaEntity));
    }

    @Override
    public void update(Integer idCliente, Customer cliente) {
        ClienteEntity clienteEntity = clienteRepositoryJpa.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException());

        clienteRepositoryJpa.save(ClienteMapperOut.actualizarEntity(clienteEntity, cliente));
    }

    @Override
    public Optional<Customer> findById(Integer idCliente) {
        return clienteRepositoryJpa.findById(idCliente).map(cliente -> {
            Person persona = PersonaMapperOut.toDomain(cliente.getPersona());
            return ClienteMapperOut.toDomain(cliente, persona);
        });
    }

    @Override
    public Optional<Customer> obtenerPorIdPersona(Integer idPersona) {
        return clienteRepositoryJpa.findByPersona_idPersona(idPersona).map(cliente -> {
            Person persona = PersonaMapperOut.toDomain(cliente.getPersona());
            return ClienteMapperOut.toDomain(cliente, persona);
        });
    }
}
