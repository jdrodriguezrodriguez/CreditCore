package com.credito.creditcore.infrastructure.adapter.out.mapper;

import java.math.BigDecimal;

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

    public static CustomerEntity toEntity(Customer customer, PersonEntity personEntity) {
        CustomerEntity customerEntity = new CustomerEntity(personEntity,
                customer.getSalary(),
                customer.getCreditHistoryScore(),
                customer.getRegistrationDate());

        customerEntity.setCustomerId(customer.getCustomerId());

        return customerEntity;
    }

    public static Customer toDomain(CustomerEntity entity, Person person) {
        return new Customer(entity.getCustomerId(),
                person,
                entity.getSalary(),
                entity.getCreditHistoryScore(),
                entity.getRegistrationDate());
    }

    public static Customer toDomain(CustomerEntity entity) {
        return new Customer(entity.getCustomerId(),
                entity.getSalary(),
                entity.getCreditHistoryScore(),
                entity.getRegistrationDate());
    }

    public static CustomerEntity updateScoreEntity(CustomerEntity customerEntity, int score) {
        customerEntity.setCreditHistoryScore(score);

        return customerEntity;
    }

    public static CustomerEntity updateSalaryEntity(CustomerEntity customerEntity, BigDecimal salary) {
        customerEntity.setSalary(salary);

        return customerEntity;
    }
}
