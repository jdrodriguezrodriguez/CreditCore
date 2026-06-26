package com.credito.creditcore.application.loan.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.loan.LoanSimulationRequestDto;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.score.DebtScore;
import com.credito.creditcore.domain.model.score.PaymentCapacityScore;
import com.credito.creditcore.domain.model.score.SeniorityScore;

@Service
public class CreditScoreService {

    /*
     * Nivel de importancia:
     * 
     * Historial : 400 pts
     * Capacidad : 300 pts
     * Endeudamiento : 200 pts
     * Antiguedad : 100 pts
     */

    private static final BigDecimal VERY_LOW_INCOME = BigDecimal.valueOf(1500000);
    private static final BigDecimal LOW_INCOME = BigDecimal.valueOf(2500000);
    private static final BigDecimal MEDIUM_INCOME = BigDecimal.valueOf(4000000);
    private static final BigDecimal UPPER_MIDDLE_INCOME = BigDecimal.valueOf(6000000);
    private static final BigDecimal HIGH_INCOME = BigDecimal.valueOf(7500000);
    private static final BigDecimal VERY_HIGH_INCOME = BigDecimal.valueOf(12000000);

    public int calculateCreditHistoryScore(Integer creditHistory) {
        int score = 0;

        // EL HISTORIAL CREDITICIO TIENE UN RANGO DE [0 , 100]
        if (creditHistory != null) {
            Double historyValue = (creditHistory / 10.0);

            score = historyValue >= 9 ? 400
                    : historyValue >= 6 ? 150
                            : historyValue >= 3 ? 60
                                    : 0;
        }

        return score;
    }

    public int calculatePaymentCapacityScore(BigDecimal salary) {
        List<PaymentCapacityScore> scoreList = List.of(
            new PaymentCapacityScore(VERY_HIGH_INCOME, 300),
            new PaymentCapacityScore(HIGH_INCOME, 240),
            new PaymentCapacityScore(UPPER_MIDDLE_INCOME, 180),
            new PaymentCapacityScore(MEDIUM_INCOME, 120),
            new PaymentCapacityScore(LOW_INCOME, 55),
            new PaymentCapacityScore(VERY_LOW_INCOME, 25)
        );

        for(PaymentCapacityScore score : scoreList){
            if (salary.compareTo(score.getSalary()) >= 0) {
                return score.getScore();
            }
        }

        return 0;
    }

    public int calculateDebtScore(BigDecimal salary, BigDecimal additionalIncome,
            BigDecimal monthlyExpenses) {

        BigDecimal totalIncome = salary.add(additionalIncome);
        BigDecimal debtRatio = monthlyExpenses.divide(totalIncome, 2, RoundingMode.HALF_UP);

        List<DebtScore> scoreList = List.of(
                new DebtScore(BigDecimal.valueOf(0.1), 200),
                new DebtScore(BigDecimal.valueOf(0.3), 180),
                new DebtScore(BigDecimal.valueOf(0.5), 140),
                new DebtScore(BigDecimal.valueOf(0.7), 80),
                new DebtScore(BigDecimal.valueOf(0.9), 25));

        for (DebtScore score : scoreList) {
            if (debtRatio.compareTo(score.getLimit()) < 0) {
                return score.getScore();
            }
        }

        return 0;
    }

    public int calculateSeniorityScore(LocalDate registrationDate) {
        
        long seniority = ChronoUnit.YEARS.between(registrationDate, LocalDate.now());

        List<SeniorityScore> scoreList = List.of(
            new SeniorityScore(5, 100),
            new SeniorityScore(4, 70),
            new SeniorityScore(3, 40),
            new SeniorityScore(2, 25),
            new SeniorityScore(1, 15)
        );

        for(SeniorityScore score : scoreList){
            if (seniority >= score.getSeniority()) {
                return score.getScore();
            }
        }

        return 0;
    }

    public int calculateTotalScore(Customer customer, LoanSimulationRequestDto request) {
        int loanScore = 0;
        // HISTORIAL(CALIFICACION DE 1 - 100)
        loanScore += calculateCreditHistoryScore(customer.getCreditHistoryScore());

        loanScore += calculatePaymentCapacityScore(customer.getSalary());

        loanScore += calculateDebtScore(customer.getSalary(),
                request.additionalIncome(),
                request.monthlyExpenses());

        loanScore += calculateSeniorityScore(customer.getRegistrationDate());

        return loanScore;
    }
}
