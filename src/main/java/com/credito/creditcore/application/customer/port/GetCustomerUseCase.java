package com.credito.creditcore.application.customer.port;

import com.credito.creditcore.domain.model.Customer;

public interface GetCustomerUseCase {
    Customer getCustomer(Integer personId);
}