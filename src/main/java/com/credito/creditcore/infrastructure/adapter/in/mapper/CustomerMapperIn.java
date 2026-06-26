package com.credito.creditcore.infrastructure.adapter.in.mapper;

import com.credito.creditcore.application.dto.customer.CustomerDto;
import com.credito.creditcore.domain.model.Customer;

public class CustomerMapperIn {
    
    public static CustomerDto createDto(Customer customer){
        return new CustomerDto(
            customer.getCustomerId(), 
            customer.getPerson(), 
            customer.getSalary(), 
            customer.getCreditHistoryScore(), 
            customer.getRegistrationDate());
    }
}
