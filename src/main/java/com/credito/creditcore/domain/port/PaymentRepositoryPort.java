package com.credito.creditcore.domain.port;

import java.math.BigDecimal;

import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.enums.Fpago;

public interface PaymentRepositoryPort {
    void savePayment(Installment cuota, Fpago fpago, BigDecimal monto);
}
