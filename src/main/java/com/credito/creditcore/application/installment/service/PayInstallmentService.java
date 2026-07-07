package com.credito.creditcore.application.installment.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credito.creditcore.application.dto.installment.PayInstallmentRequestDto;
import com.credito.creditcore.application.installment.port.PayInstallmentUseCase;
import com.credito.creditcore.domain.excepcion.LateFeePaymentRequiredException;
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
        
        private static final BigDecimal DAILY_LATE_RATE = new BigDecimal("0.002");
        private static final Logger logger = Logger.getLogger(PayInstallmentService.class.getName());


        public PayInstallmentService(
                        InstallmentRepositoryPort installmentRepositoryPort, LoanRepositoryPort loanRepositoryPort,
                        PaymentRepositoryPort paymentRepositoryPort) {
                this.installmentRepositoryPort = installmentRepositoryPort;
                this.loanRepositoryPort = loanRepositoryPort;
                this.paymentRepositoryPort = paymentRepositoryPort;
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

                verifyPaymentFee(installment);

                // paidAmount
                if (request.amountToPay().compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException(
                                        "Amount must be greater than 0");
                }

                if (installment.getInstallmentAmount().compareTo(request.amountToPay()) != 0) {
                        throw new IllegalArgumentException(
                                        "Amount must be equals than installment Amount");
                }

                /*------------------------------------------------------------------------------------------------------ */

                installment.setStatus(InstallmentStatus.PAID);
                installment.setPaidAmount(request.amountToPay());
                installment.setActualPaymentDate(request.actualPaymentDate());

                BigDecimal lateFree = calculateLateFree(installment, request);

                processLateFee(lateFree, installment);

                installmentRepositoryPort.updateInstallment(
                                installment);

                paymentRepositoryPort.savePayment(
                                installment,
                                request.paymentMethod(),
                                request.amountToPay());

                loanRepositoryPort.updateTotalPaid(installment.getPaidAmount(), loan.getLoanId());
        }

        // lateFree = amountInstalment × 0.0005 × days
        private BigDecimal calculateLateFree(Installment installment, PayInstallmentRequestDto request) {

                if (request.actualPaymentDate().compareTo(installment.getDueDate()) > 0) {

                        Long daysLate = ChronoUnit.DAYS.between(installment.getDueDate(), request.actualPaymentDate());
                
                        BigDecimal lateFree = installment.getInstallmentAmount()
                                        .multiply(DAILY_LATE_RATE)
                                        .multiply(BigDecimal.valueOf(daysLate)).setScale(0, RoundingMode.HALF_UP);

                        return lateFree;
                }
                return BigDecimal.ZERO;
        }

        private void processLateFee(BigDecimal lateFree, Installment installment) {
                if (lateFree.compareTo(BigDecimal.ZERO) != 0) {

                        BigDecimal difference = installment.getPaidAmount().subtract(lateFree);

                        if (difference.compareTo(installment.getInstallmentAmount()) < 0) {

                                installment.setLateFee(lateFree);
                                installment.setStatus(InstallmentStatus.PARTIALLY);

                                logger.info("Pago la cuota, pero debe la mora");
                        }
                }
        }

        private void verifyPaymentFee(Installment installment){
                if (installment.getInstallmentNumber() > 1) {
                        Integer previousInstallmentNumber = installment.getInstallmentNumber() - 1;

                        Installment previousInstallment = installmentRepositoryPort
                                        .findByLoanIdAndNumber(installment.getLoan().getLoanId(),
                                                        previousInstallmentNumber)
                                        .orElseThrow(() -> new IllegalArgumentException(
                                                        "Installment not found with Installment Number: "
                                                                        + previousInstallmentNumber));

                        if (previousInstallment.getLateFee() != null
                                        && previousInstallment.getLateFee().compareTo(BigDecimal.ZERO) != 0) {
                                throw new LateFeePaymentRequiredException(
                                                "Must pay the late payment fee. Check the previous installment.");
                                }
                }
        }
}