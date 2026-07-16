package com.credito.creditcore.domain.port;

import com.credito.creditcore.domain.model.Payment;

public interface PaymentRepositoryPort {
    void savePayment(Payment payment);
}
