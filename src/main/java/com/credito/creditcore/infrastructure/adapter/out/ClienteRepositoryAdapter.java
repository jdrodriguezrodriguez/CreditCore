package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.domain.port.ClienteRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.ClienteMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.persistence.ClienteRepositoryJpa;
import com.credito.creditcore.infrastructure.persistence.PersonaRepositoryJpa;

public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteRepositoryJpa clienteRepositoryJpa;
    private final PersonaRepositoryJpa personaRepositoryJpa;

    public ClienteRepositoryAdapter(ClienteRepositoryJpa clienteRepositoryJpa,
            PersonaRepositoryJpa personaRepositoryJpa) {
        this.clienteRepositoryJpa = clienteRepositoryJpa;
        this.personaRepositoryJpa = personaRepositoryJpa;
    }

    @Override
    public void guardar(Cliente cliente, Integer idPersona) {
        PersonaEntity personaEntity = personaRepositoryJpa.findById(idPersona)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        clienteRepositoryJpa.save(ClienteMapperOut.crearEntidad(cliente, personaEntity));
    }

    @Override
    public void actualizar(Integer idCliente, Cliente cliente) {
        ClienteEntity clienteEntity = clienteRepositoryJpa.findById(idCliente)
            .orElseThrow(() -> new RuntimeException("Error accediendo a datos"));

        clienteEntity.setSalario(cliente.getSalario());
        clienteEntity.setHistorialCrediticio(cliente.getHistorialCrediticio());

        clienteRepositoryJpa.save(clienteEntity);
    }

    @Override
    public Optional<Cliente> consultar(Integer idCliente) {
        return clienteRepositoryJpa.findById(idCliente).map(cliente -> {
            Persona persona = PersonaMapperOut.toDomain(cliente.getPersona());
            return ClienteMapperOut.toDomain(cliente, persona);
        });
    }

}
