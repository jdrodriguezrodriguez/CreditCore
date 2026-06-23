package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;

public class ClienteMapperOut {

    public static ClienteEntity crearEntidad(Customer customer, PersonaEntity personaEntity) {
        return new ClienteEntity(personaEntity,
                customer.getSalary(), 
                customer.getCreditHistoryScore(), 
                customer.getRegistrationDate());
    }
    
    public static ClienteEntity toEntity(Customer customer, PersonaEntity personaEntity){
        ClienteEntity clienteEntity = new ClienteEntity(personaEntity,
                customer.getSalary(), 
                customer.getCreditHistoryScore(), 
                customer.getRegistrationDate());

        clienteEntity.setIdCliente(customer.getCustomerId());

        return clienteEntity;
    }

    public static Customer toDomain(ClienteEntity entity, Person persona){
        return new Customer(entity.getIdCliente(),
         persona, 
         entity.getSalario(),
         entity.getHistorialCrediticio(), 
         entity.getFechaRegistro());
    }

    public static ClienteEntity actualizarEntity(ClienteEntity clienteEntity, Customer customer){
        clienteEntity.setSalario(customer.getSalary());
        clienteEntity.setHistorialCrediticio(customer.getCreditHistoryScore());

        return clienteEntity;
    }
}
