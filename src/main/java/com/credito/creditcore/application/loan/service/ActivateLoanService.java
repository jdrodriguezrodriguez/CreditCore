package com.credito.creditcore.application.loan.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.loan.port.ActivateLoanUseCase;
import com.credito.creditcore.application.loan.port.FrenchAmortizationService;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.domain.model.enums.InstallmentStatus;
import com.credito.creditcore.domain.model.enums.LoanStatus;
import com.credito.creditcore.domain.model.score.AmortizationInstallment;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;
import com.credito.creditcore.domain.port.LoanRepositoryPort;

@Service
public class ActivateLoanService implements ActivateLoanUseCase {

        private final LoanRepositoryPort loanRepositoryPort;
        private final CustomerRepositoryPort customerRepositoryPort;
        private final InstallmentRepositoryPort installmentRepositoryPort;

        private final FrenchAmortizationService frenchAmortizationService;

        public ActivateLoanService(LoanRepositoryPort loanRepositoryPort,
                        CustomerRepositoryPort customerRepositoryPort,
                        InstallmentRepositoryPort installmentRepositoryPort,
                        FrenchAmortizationService frenchAmortizationService) {
                this.loanRepositoryPort = loanRepositoryPort;
                this.customerRepositoryPort = customerRepositoryPort;
                this.installmentRepositoryPort = installmentRepositoryPort;
                this.frenchAmortizationService = frenchAmortizationService;
        }

        @Override
        public void activateLoan(Integer personId) {

                Customer customer = customerRepositoryPort.findByPersonId(personId)
                                .orElseThrow(
                                                () -> new IllegalArgumentException(
                                                                "Customer not found with person ID " + personId));

                Loan loan = loanRepositoryPort.findByCustomerId(customer.getCustomerId())
                                .orElseThrow(
                                                () -> new IllegalArgumentException("Loan not found with customer ID: "
                                                                + customer.getCustomerId()));

                loan.setApprovalDate(LocalDate.now());
                loan.setLoanStatus(LoanStatus.ACTIVE);

                List<Installment> installments = createInstallments(loan);

                loanRepositoryPort.update(loan);
                installmentRepositoryPort.saveInstallments(installments, loan, customer);
        }

        private List<Installment> createInstallments(Loan loan) {

                List<Installment> installments = new ArrayList<>();
                List<AmortizationInstallment> table = frenchAmortizationService.generateAmortizationSchedule(
                                loan.getPrincipalAmount(),
                                loan.getTermInMonths());

                for (int i = 0; i < loan.getTermInMonths(); i++) {

                        AmortizationInstallment amortizationInstallment = table.get(i);
                        LocalDate dueDate = loan.getApprovalDate().plusMonths(i + 1);

                        Installment installment = new Installment(
                                        null,
                                        loan,
                                        i + 1,
                                        dueDate,
                                        InstallmentStatus.PENDING,
                                        amortizationInstallment.getInitialBalance(),
                                        amortizationInstallment.getInterest(),
                                        amortizationInstallment.getAmortization(),
                                        amortizationInstallment.getInstallmentAmount(),
                                        amortizationInstallment.getFinalBalance(),
                                        BigDecimal.ZERO,
                                        BigDecimal.ZERO,
                                        null);

                        installments.add(installment);
                }

                return installments;
        }
}
