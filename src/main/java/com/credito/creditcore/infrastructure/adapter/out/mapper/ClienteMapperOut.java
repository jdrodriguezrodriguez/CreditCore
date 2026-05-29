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
    
    public static ClienteEntity toEntity(Cliente cliente, PersonaEntity personaEntity){
        ClienteEntity clienteEntity = new ClienteEntity(personaEntity,
                cliente.getSalario(),
                cliente.getHistorialCrediticio(),
                cliente.getFechaRegistro());

        clienteEntity.setIdCliente(cliente.getIdCliente());

        return clienteEntity;
    }

    public static Cliente toDomain(ClienteEntity entity, Persona persona){
        return new Cliente(entity.getIdCliente(),
         persona, 
         entity.getSalario(),
         entity.getHistorialCrediticio(), 
         entity.getFechaRegistro());
    }

    public static ClienteEntity actualizarEntity(ClienteEntity clienteEntity, Cliente cliente){
        clienteEntity.setSalario(cliente.getSalario());
        clienteEntity.setHistorialCrediticio(cliente.getHistorialCrediticio());

        return clienteEntity;
    }
}
