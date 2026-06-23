package com.credito.creditcore.application.loan.port;

import java.math.BigDecimal;
import java.util.List;

import com.credito.creditcore.domain.model.score.AmortizationInstallment;

public interface FrenchAmortizationService {

        BigDecimal calculateMonthlyInstallment(BigDecimal loanAmount, int termMonths);

        BigDecimal calculateTotalAmountPayable(BigDecimal loanAmount, int termMonths);

        BigDecimal calculateTotalInterest(BigDecimal loanAmount, int termMonths);

        List<AmortizationInstallment> generateAmortizationSchedule(BigDecimal loanAmount, int termMonths);
}
