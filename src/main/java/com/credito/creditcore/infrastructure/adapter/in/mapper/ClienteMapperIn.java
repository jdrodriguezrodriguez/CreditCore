package com.credito.creditcore.infrastructure.adapter.in.mapper;

import com.credito.creditcore.application.dto.cliente.ClienteDto;
import com.credito.creditcore.domain.model.Customer;

public class ClienteMapperIn {
    
    public static ClienteDto crearDto(Customer cliente){
        return new ClienteDto(
            cliente.getIdCliente(), 
            cliente.getPersona(), 
            cliente.getSalario(), 
            cliente.getHistorialCrediticio(), 
            cliente.getFechaRegistro());
    }
}
