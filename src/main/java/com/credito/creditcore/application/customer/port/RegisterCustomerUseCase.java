package com.credito.creditcore.application.customer.port;

import java.math.BigDecimal;

public interface RegisterCustomerUseCase {
    void registerCustomer(BigDecimal salary, Integer personId);
}
