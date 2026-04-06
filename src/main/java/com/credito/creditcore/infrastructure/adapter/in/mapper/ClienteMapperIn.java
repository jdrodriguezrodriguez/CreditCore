package com.credito.creditcore.infrastructure.adapter.in.mapper;

import com.credito.creditcore.application.dto.ClienteDto;
import com.credito.creditcore.domain.model.Cliente;

public class ClienteMapperIn {
    
    public static ClienteDto crearDto(Cliente cliente){
        return new ClienteDto(
            cliente.getIdCliente(), 
            cliente.getPersona(), 
            cliente.getSalario(), 
            cliente.getHistorialCrediticio(), 
            cliente.getFechaRegistro());
    }
}
