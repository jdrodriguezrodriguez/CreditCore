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

        customerEntity.setCustomerId(customer.getCustomerId());

        return customerEntity;
    }

    public static Customer toDomain(CustomerEntity entity, Person person){
        return new Customer(entity.getCustomerId(),
         person, 
         entity.getSalary(),
         entity.getCreditHistoryScore(), 
         entity.getRegistrationDate());
    }

    public static CustomerEntity updateEntity(CustomerEntity customerEntity, Customer customer){
        customerEntity.setSalary(customer.getSalary());
        customerEntity.setCreditHistoryScore(customer.getCreditHistoryScore());

        return customerEntity;
    }
}
