package com.credito.creditcore.application.installment.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credito.creditcore.application.dto.installment.PayInstallmentRequestDto;
import com.credito.creditcore.application.installment.port.PayInstallmentUseCase;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.domain.model.Payment;
import com.credito.creditcore.domain.model.enums.InstallmentStatus;
import com.credito.creditcore.domain.model.enums.LoanStatus;
import com.credito.creditcore.domain.model.enums.PaymentConcept;
import com.credito.creditcore.domain.model.score.ScoreIncrease;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;
import com.credito.creditcore.domain.port.LoanRepositoryPort;
import com.credito.creditcore.domain.port.PaymentRepositoryPort;

@Transactional
@Service
public class PayInstallmentService implements PayInstallmentUseCase {

        private final InstallmentRepositoryPort installmentRepositoryPort;
        private final LoanRepositoryPort loanRepositoryPort;
        private final PaymentRepositoryPort paymentRepositoryPort;
        private final CustomerRepositoryPort customerRepositoryPort;

        private static final Logger logger = Logger.getLogger(PayInstallmentService.class.getName());
        private static final BigDecimal MINIMUM_PARTIAL_PAYMENT = BigDecimal.valueOf(50_000);

        private final LateFeeService lateFeeValidationService;

        public PayInstallmentService(
                        InstallmentRepositoryPort installmentRepositoryPort, LoanRepositoryPort loanRepositoryPort,
                        PaymentRepositoryPort paymentRepositoryPort, LateFeeService lateFeeValidationService,
                        CustomerRepositoryPort customerRepositoryPort) {
                this.installmentRepositoryPort = installmentRepositoryPort;
                this.loanRepositoryPort = loanRepositoryPort;
                this.paymentRepositoryPort = paymentRepositoryPort;
                this.lateFeeValidationService = lateFeeValidationService;
                this.customerRepositoryPort = customerRepositoryPort;
        }

        @Override
        public void payInstallment(
                        Integer installmentId,
                        PayInstallmentRequestDto request) {

                Installment installment = installmentRepositoryPort.findById(installmentId)
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "Installment not found with ID: " + installmentId));
                Loan loan = loanRepositoryPort.findByLoanId(installment.getLoan().getLoanId())
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "Loan not found with ID: " + installment.getLoan().getLoanId()));

                lateFeeValidationService.verifyPreviousLateFee(installment);

                validateData(installment, request);

                if (installment.getStatus() == InstallmentStatus.PARTIALLY) {
                        processPartialPayment(installment, request);
                } else {
                        processNormalPayment(installment, request);
                }

                BigDecimal lateFree = lateFeeValidationService.calculateLateFee(installment,
                                request.actualPaymentDate());

                applyLateFee(lateFree, installment);

                installmentRepositoryPort.updateInstallment(
                                installment);

                Payment payment = Payment.create(
                                installment,
                                request.amountToPay(),
                                request.paymentMethod(),
                                request.paymentConcept());

                paymentRepositoryPort.savePayment(
                                payment);

                loanRepositoryPort.updateTotalPaid(request.amountToPay(), loan.getLoanId());
        }

        private void applyLateFee(BigDecimal lateFree, Installment installment) {
                if (lateFree.compareTo(BigDecimal.ZERO) != 0) {

                        BigDecimal difference = installment.getPaidAmount().subtract(lateFree);

                        if (difference.compareTo(installment.getInstallmentAmount()) < 0) {

                                installment.setLateFee(lateFree);
                                installment.setStatus(InstallmentStatus.PARTIALLY);

                                logger.info("The installment was paid, but the late payment fee is still due, and this will continue to increase.");
                        }
                }
        }

        private void processPartialPayment(Installment installment, PayInstallmentRequestDto request) {

                BigDecimal total = installment.getPaidAmount().add(request.amountToPay());

                if (request.amountToPay().compareTo(MINIMUM_PARTIAL_PAYMENT) <= 0) {

                        if (total.compareTo(installment.getInstallmentAmount()) < 0) {
                                throw new IllegalArgumentException(
                                                "Amount must be greater than $50.000");
                        }
                }

                if (total.compareTo(installment.getInstallmentAmount()) >= 0) {
                        installment.setStatus(InstallmentStatus.PAID);
                        logger.info("The installment was paid.");

                        if (isLastInstallment(installment)) {
                                int score = calculateScoreIncrease(installment.getLoan().getLoanId());
                                updateCustomerCreditScore(installment.getLoan(), score);
                                updateStatusLoan(installment.getLoan());
                        }

                } else {
                        logger.info("The installment was recorded partially.");
                }

                installment.setPaidAmount(total);
                installment.setActualPaymentDate(request.actualPaymentDate());
        }

        private void processNormalPayment(Installment installment, PayInstallmentRequestDto request) {
                if (request.amountToPay().compareTo(installment.getInstallmentAmount()) < 0) {
                        installment.setStatus(InstallmentStatus.PARTIALLY);
                        logger.info("The installment was recorded partially.");

                } else if (request.amountToPay().compareTo(installment.getInstallmentAmount()) >= 0) {
                        installment.setStatus(InstallmentStatus.PAID);
                        logger.info("The installment was paid.");

                        if (isLastInstallment(installment)) {
                                int score = calculateScoreIncrease(installment.getLoan().getLoanId());
                                updateCustomerCreditScore(installment.getLoan(), score);
                                updateStatusLoan(installment.getLoan());
                        }
                }

                installment.setPaidAmount(request.amountToPay());
                installment.setActualPaymentDate(request.actualPaymentDate());
        }

        private void validateData(Installment installment, PayInstallmentRequestDto request) {
                if (installment.getInstallmentAmount().compareTo(installment.getPaidAmount()) == 0) {
                        throw new IllegalArgumentException(
                                        "The installment has already been paid.");
                }

                if (request.amountToPay().compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException(
                                        "Amount must be greater than 0");
                }
        }

        private boolean isLastInstallment(Installment installment) {

                Integer nextInstallmentNumber = installment.getInstallmentNumber() + 1;

                return installmentRepositoryPort
                                .findByLoanIdAndNumber(installment.getLoan().getLoanId(),
                                                nextInstallmentNumber)
                                .isEmpty();
        }

        private int calculateScoreIncrease(Integer loanId) {
                List<Payment> payments = paymentRepositoryPort.findByLoanId(loanId);

                Long lateCount = payments.stream().filter(p -> p.getPaymentConcept() == PaymentConcept.LATE_FEE)
                                .count();

                Integer late = Math.toIntExact(lateCount);

                List<ScoreIncrease> table = List.of(
                                new ScoreIncrease(0, 10),
                                new ScoreIncrease(2, 7),
                                new ScoreIncrease(5, 4));

                for (ScoreIncrease row : table) {
                        if (late.compareTo(row.getCountLate()) <= 0) {
                                return row.getScore();
                        }
                }

                return 0;
        }

        private void updateCustomerCreditScore(Loan loan, int score) {

                Customer customer = customerRepositoryPort.findById(loan.getCustomer().getCustomerId())
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "Customer not found with ID: " + loan.getCustomer().getCustomerId()));

                int newScore = Math.min(customer.getCreditHistoryScore() + score, 100);

                customerRepositoryPort.updateCreditScore(loan.getCustomer().getCustomerId(), newScore);
        }

        private void updateStatusLoan(Loan loan) {
                loanRepositoryPort.updateLoanStatus(LoanStatus.COMPLETED, loan.getLoanId());
        }
}