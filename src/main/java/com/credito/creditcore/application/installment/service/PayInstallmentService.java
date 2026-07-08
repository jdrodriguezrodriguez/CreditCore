package com.credito.creditcore.application.installment.service;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credito.creditcore.application.dto.installment.PayInstallmentRequestDto;
import com.credito.creditcore.application.installment.port.PayInstallmentUseCase;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.domain.model.enums.InstallmentStatus;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;
import com.credito.creditcore.domain.port.LoanRepositoryPort;
import com.credito.creditcore.domain.port.PaymentRepositoryPort;

@Transactional
@Service
public class PayInstallmentService implements PayInstallmentUseCase {

        private final InstallmentRepositoryPort installmentRepositoryPort;
        private final LoanRepositoryPort loanRepositoryPort;
        private final PaymentRepositoryPort paymentRepositoryPort;

        private static final Logger logger = Logger.getLogger(PayInstallmentService.class.getName());

        private final LateFeeService lateFeeValidationService;

        public PayInstallmentService(
                        InstallmentRepositoryPort installmentRepositoryPort, LoanRepositoryPort loanRepositoryPort,
                        PaymentRepositoryPort paymentRepositoryPort,
                        LateFeeService lateFeeValidationService) {
                this.installmentRepositoryPort = installmentRepositoryPort;
                this.loanRepositoryPort = loanRepositoryPort;
                this.paymentRepositoryPort = paymentRepositoryPort;
                this.lateFeeValidationService = lateFeeValidationService;
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

                // paidAmount
                if (request.amountToPay().compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException(
                                        "Amount must be greater than 0");
                }

                if (installment.getInstallmentAmount().compareTo(request.amountToPay()) != 0) {
                        throw new IllegalArgumentException(
                                        "Amount must be equals than installment Amount");
                }


                installment.setStatus(InstallmentStatus.PAID);
                installment.setPaidAmount(request.amountToPay());
                installment.setActualPaymentDate(request.actualPaymentDate());

                BigDecimal lateFree = lateFeeValidationService.calculateLateFee(installment, request.actualPaymentDate());

                processLateFee(lateFree, installment);

                installmentRepositoryPort.updateInstallment(
                                installment);

                paymentRepositoryPort.savePayment(
                                installment,
                                request.paymentMethod(),
                                request.amountToPay());

                loanRepositoryPort.updateTotalPaid(installment.getPaidAmount(), loan.getLoanId());
        }

        private void processLateFee(BigDecimal lateFree, Installment installment) {
                if (lateFree.compareTo(BigDecimal.ZERO) != 0) {

                        BigDecimal difference = installment.getPaidAmount().subtract(lateFree);

                        if (difference.compareTo(installment.getInstallmentAmount()) < 0) {

                                installment.setLateFee(lateFree);
                                installment.setStatus(InstallmentStatus.PARTIALLY);

                                logger.info("Pago la cuota, pero debe la mora, esta continuara incrementando.");
                        }
                }
        }
}