package com.credito.creditcore.application.customer.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.customer.port.UpdateCustomerUseCase;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;

@Service
public class UpdateCustomerService implements UpdateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public UpdateCustomerService(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public void updateCustomer(Integer customerId, Customer customer) {
        customerRepositoryPort.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID " + customerId));

        customerRepositoryPort.update(customerId, customer);
    }

}
