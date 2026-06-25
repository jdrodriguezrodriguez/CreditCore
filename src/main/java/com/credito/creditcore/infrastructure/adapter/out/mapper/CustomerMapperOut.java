package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.infrastructure.entity.CustomerEntity;
import com.credito.creditcore.infrastructure.entity.PersonEntity;

public class CustomerMapperOut {

    public static CustomerEntity createEntity(Customer customer, PersonEntity personEntity) {
        return new CustomerEntity(personEntity,
                customer.getSalary(), 
                customer.getCreditHistoryScore(), 
                customer.getRegistrationDate());
    }
    
    public static CustomerEntity toEntity(Customer customer, PersonEntity personEntity){
        CustomerEntity customerEntity = new CustomerEntity(personEntity,
                customer.getSalary(), 
                customer.getCreditHistoryScore(), 
                customer.getRegistrationDate());

        customerEntity.setIdCliente(customer.getCustomerId());

        return customerEntity;
    }

    public static Customer toDomain(CustomerEntity entity, Person person){
        return new Customer(entity.getIdCliente(),
         person, 
         entity.getSalario(),
         entity.getHistorialCrediticio(), 
         entity.getFechaRegistro());
    }

    public static CustomerEntity updateEntity(CustomerEntity customerEntity, Customer customer){
        customerEntity.setSalario(customer.getSalary());
        customerEntity.setHistorialCrediticio(customer.getCreditHistoryScore());

        return customerEntity;
    }
}
