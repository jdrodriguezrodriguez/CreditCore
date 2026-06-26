package com.credito.creditcore.application.customer.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.customer.port.GetCustomerUseCase;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;

@Service
public class GetCustomerService implements GetCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public GetCustomerService(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Customer getCustomer(Integer customerId) {
        return customerRepositoryPort.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer not found with ID: " + customerId));
    }
}
