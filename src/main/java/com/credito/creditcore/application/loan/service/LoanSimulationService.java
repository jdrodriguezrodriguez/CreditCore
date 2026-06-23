package com.credito.creditcore.application.loan.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.prestamo.AmortizationResponseDto;
import com.credito.creditcore.application.dto.prestamo.LoanSimulationRequestDto;
import com.credito.creditcore.application.dto.prestamo.LoanSimulationResponseDto;
import com.credito.creditcore.application.loan.domain.service.CreditScoreService;
import com.credito.creditcore.application.loan.port.FrenchAmortizationService;
import com.credito.creditcore.application.loan.port.SimulateLoanUseCase;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.enums.ApprovalEstimate;
import com.credito.creditcore.domain.model.score.AmortizationInstallment;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;

@Service
public class LoanSimulationService implements SimulateLoanUseCase {

    private static final double MONTHLY_INTEREST_RATE = 0.02;
    private static final int SCORE_REJECTED = 160;
    private static final int SCORE_PARTIAL_APPROVAL = 600;

    private final CustomerRepositoryPort customerRepositoryPort;

    private final CreditScoreService creditScoreService;
    private final FrenchAmortizationService frenchAmortizationService;

    public LoanSimulationService(CustomerRepositoryPort customerRepositoryPort,
            CreditScoreService creditScoreService,
            FrenchAmortizationService frenchAmortizationService) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.creditScoreService = creditScoreService;
        this.frenchAmortizationService = frenchAmortizationService;
    }

    @Override
    public LoanSimulationResponseDto simulateLoan(LoanSimulationRequestDto request, Integer personId) {

        Customer customer = customerRepositoryPort.findByPersonId(personId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Customer not found with person ID: " + personId));

        int loanScore = creditScoreService.calculateTotalScore(customer, request);

        ApprovalEstimate estimate = estimateApproval(loanScore);

        BigDecimal monthlyInstallment = frenchAmortizationService.calculateMonthlyInstallment(request.loanAmount(), request.termInMonths());
        BigDecimal totalAmountPayable = frenchAmortizationService.calculateTotalAmountPayable(request.loanAmount(), request.termInMonths());
        BigDecimal totalInterest = frenchAmortizationService.calculateTotalInterest(request.loanAmount(), request.termInMonths());

        List<AmortizationInstallment> table = frenchAmortizationService.generateAmortizationSchedule(request.loanAmount(),
                request.termInMonths());

        List<AmortizationResponseDto> amortizationResponseList = table.stream().map(
                installment  -> new AmortizationResponseDto(
                        installment.getInstallmentNumber(),
                        installment.getInitialBalance(),
                        installment.getInterest(),
                        installment.getAmortization(),
                        installment.getInstallmentAmount(),
                        installment.getFinalBalance()))
                .toList();

        return new LoanSimulationResponseDto(
                request.loanAmount(),
                monthlyInstallment,
                totalAmountPayable,
                totalInterest,
                request.termInMonths(),
                MONTHLY_INTEREST_RATE,
                loanScore,
                estimate,
                request.loanType(), // POSIBLES AJUSTES POR EL TIPO DE PRESTAMO
                amortizationResponseList);
    }

    private ApprovalEstimate estimateApproval(int loanScore) {
        if (loanScore < SCORE_REJECTED) {
            return ApprovalEstimate.REJECTION;
        } else if (loanScore < SCORE_PARTIAL_APPROVAL) {
            return ApprovalEstimate.POSSIBLE_APPROVAL;
        } else {
            return ApprovalEstimate.APPROVAL;
        }
    }
}
