package com.credito.creditcore.domain.port;

import java.util.List;
import java.util.Optional;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.Loan;

public interface InstallmentRepositoryPort {
    void saveInstallments(List<Installment> installments, Loan loan, Customer customer);
    Optional<Installment> findById(Integer idInstallment);
    List<Installment> findByLoanId(Integer customerId);
    void updateInstallment(Installment installment);
    Optional<Installment> findByLoanIdAndNumber(Integer loanId, Integer installmentNumber);
}
