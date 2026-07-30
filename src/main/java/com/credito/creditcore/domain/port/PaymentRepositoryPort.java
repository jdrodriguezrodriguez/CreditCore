package com.credito.creditcore.domain.port;

import java.util.List;

import com.credito.creditcore.domain.model.Payment;

public interface PaymentRepositoryPort {
    void savePayment(Payment payment);
    List<Payment> findByLoanId(Integer loanId);
}
