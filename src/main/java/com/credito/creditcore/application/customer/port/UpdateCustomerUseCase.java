package com.credito.creditcore.application.customer.port;

import com.credito.creditcore.domain.model.Customer;

public interface UpdateCustomerUseCase {
    void updateCustomer(Integer idCliente, Customer cliente);
}
