package com.credito.creditcore.application.customer.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.customer.port.RegisterCustomerUseCase;
import com.credito.creditcore.domain.excepcion.PersonNotFoundException;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;
import com.credito.creditcore.domain.port.PersonRepositoryPort;

@Service
public class RegisterCustomerService implements RegisterCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final PersonRepositoryPort personRepositoryPort;

    public RegisterCustomerService(CustomerRepositoryPort customerRepositoryPort,
            PersonRepositoryPort personRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.personRepositoryPort = personRepositoryPort;
    }

    @Override
    public void registerCustomer(BigDecimal salary, Integer personId) {

        Person person = personRepositoryPort.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with ID: " + personId));

        Integer creditScore  = 0;

        Customer newCustomer = Customer.create(salary, creditScore, person);

        customerRepositoryPort.save(newCustomer);
    }

    /*
     * Historial de pagos → 40% (400 pts)
     * Capacidad de pago → 30% (300 pts)
     * Endeudamiento actual → 20% (200 pts)
     * Antigüedad como cliente → 10% (100 pts)
     */
}
