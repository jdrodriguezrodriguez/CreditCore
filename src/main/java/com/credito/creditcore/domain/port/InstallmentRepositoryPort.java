package com.credito.creditcore.domain.port;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.Loan;

public interface InstallmentRepositoryPort {
    void saveInstallments(List<Installment> cuotas, Loan prestamo, Customer cliente);
    Optional<Installment> findById(Integer idCuota);
    List<Installment> findByCustomerId(Integer idCliente);
    void updateInstallment(Integer idCuota, BigDecimal monto);
}
