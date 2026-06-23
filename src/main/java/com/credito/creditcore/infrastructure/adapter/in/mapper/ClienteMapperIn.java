package com.credito.creditcore.infrastructure.adapter.in.mapper;

import com.credito.creditcore.application.dto.cliente.ClienteDto;
import com.credito.creditcore.domain.model.Customer;

public class ClienteMapperIn {
    
    public static ClienteDto crearDto(Customer customer){
        return new ClienteDto(
            customer.getCustomerId(), 
            customer.getPerson(), 
            customer.getSalary(), 
            customer.getCreditHistoryScore(), 
            customer.getRegistrationDate());
    }
}
