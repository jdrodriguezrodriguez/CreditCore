package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;

public class ClienteMapperOut {

    public static ClienteEntity crearEntidad(Cliente cliente, PersonaEntity personaEntity) {
        return new ClienteEntity(personaEntity,
                cliente.getSalario(),
                cliente.getHistorialCrediticio(),
                cliente.getFechaRegistro());
    }

    public static Cliente toDomain(ClienteEntity entity, Persona persona){
        return new Cliente(entity.getIdCliente(),
         persona, 
         entity.getSalario(),
         entity.getHistorialCrediticio(), 
         entity.getFechaRegistro());
    }
}
