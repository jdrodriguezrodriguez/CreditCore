package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;

public class ClienteMapperOut {

    public static ClienteEntity crearEntidad(Customer cliente, PersonaEntity personaEntity) {
        return new ClienteEntity(personaEntity,
                cliente.getSalario(),
                cliente.getHistorialCrediticio(),
                cliente.getFechaRegistro());
    }
    
    public static ClienteEntity toEntity(Customer cliente, PersonaEntity personaEntity){
        ClienteEntity clienteEntity = new ClienteEntity(personaEntity,
                cliente.getSalario(),
                cliente.getHistorialCrediticio(),
                cliente.getFechaRegistro());

        clienteEntity.setIdCliente(cliente.getIdCliente());

        return clienteEntity;
    }

    public static Customer toDomain(ClienteEntity entity, Person persona){
        return new Customer(entity.getIdCliente(),
         persona, 
         entity.getSalario(),
         entity.getHistorialCrediticio(), 
         entity.getFechaRegistro());
    }

    public static ClienteEntity actualizarEntity(ClienteEntity clienteEntity, Customer cliente){
        clienteEntity.setSalario(cliente.getSalario());
        clienteEntity.setHistorialCrediticio(cliente.getHistorialCrediticio());

        return clienteEntity;
    }
}
