package com.credito.creditcore.domain.port;

import java.math.BigDecimal;

import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.enums.PaymentMethod;

public interface PaymentRepositoryPort {
    void savePayment(Installment installment, PaymentMethod methodPayment, BigDecimal amount);
}
