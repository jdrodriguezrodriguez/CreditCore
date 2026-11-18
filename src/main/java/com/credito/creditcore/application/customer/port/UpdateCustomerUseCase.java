package com.credito.creditcore.application.customer.port;

import java.math.BigDecimal;

public interface UpdateCustomerUseCase {
    void updateCustomer(Integer customerId, BigDecimal salary);
}
