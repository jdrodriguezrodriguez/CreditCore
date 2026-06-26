package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.CustomerMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonMapperOut;
import com.credito.creditcore.infrastructure.entity.CustomerEntity;
import com.credito.creditcore.infrastructure.entity.PersonEntity;
import com.credito.creditcore.infrastructure.persistence.CustomerRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final CustomerRepositoryJpa customerRepositoryJpa;

    public CustomerRepositoryAdapter(CustomerRepositoryJpa customerRepositoryJpa) {
        this.customerRepositoryJpa = customerRepositoryJpa;
    }

    @Override
    public void save(Customer customer) {

        PersonEntity personEntity = PersonMapperOut.toEntity(customer.getPerson());

        customerRepositoryJpa.save(CustomerMapperOut.createEntity(customer, personEntity));
    }

    @Override
    public void update(Integer idCliente, Customer customer) {
        CustomerEntity customerEntity = customerRepositoryJpa.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException());

        customerRepositoryJpa.save(CustomerMapperOut.updateEntity(customerEntity, customer));
    }

    @Override
    public Optional<Customer> findById(Integer customerId) {
        return customerRepositoryJpa.findById(customerId).map(customerEntity -> {
            Person person = PersonMapperOut.toDomain(customerEntity.getPerson());
            return CustomerMapperOut.toDomain(customerEntity, person);
        });
    }

    @Override
    public Optional<Customer> findByPersonId(Integer personId) {
        return customerRepositoryJpa.findByPerson_PersonId(personId).map(customerEntity -> {
            Person person = PersonMapperOut.toDomain(customerEntity.getPerson());
            return CustomerMapperOut.toDomain(customerEntity, person);
        });
    }
}
