package com.credito.creditcore.application.loan.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.loan.port.FrenchAmortizationService;
import com.credito.creditcore.domain.model.score.AmortizationInstallment;


@Service
public class FrenchAmortizationServiceImpl implements FrenchAmortizationService{

    // private final double INTERES_BASE_MENSUAL = 0.02;

    // TEST
    private final BigDecimal MONTHLY_INTEREST_RATE = BigDecimal.valueOf(0.045);

    /*
     * C = cuota mensual
     * c = capital
     * P = préstamo
     * r = interés mensual (tasa)
     * n = número de cuotas
     * t = total Pagado
     */

    // Cuota Mensual: c = p * r(1 + r)^n / (1 + r)^n - 1
    @Override
    public BigDecimal calculateMonthlyInstallment(BigDecimal loanAmount, int termMonths) {

        BigDecimal interestRate = MONTHLY_INTEREST_RATE ;
        BigDecimal onePlusRate = BigDecimal.valueOf(1).add(interestRate);
        BigDecimal power = onePlusRate .pow(termMonths);

        BigDecimal numerator = interestRate.multiply(power);
        BigDecimal denominator = power.subtract(BigDecimal.valueOf(1));

        BigDecimal monthlyInstallment = loanAmount.multiply(numerator).divide(denominator, 2, RoundingMode.HALF_UP);

        return monthlyInstallment;
    }

    // t = C * n
    @Override
    public BigDecimal calculateTotalAmountPayable(BigDecimal loanAmount, int termMonths) {
        return calculateMonthlyInstallment(loanAmount, termMonths).multiply(BigDecimal.valueOf(termMonths));
    }

    // I = t - P
    @Override
    public BigDecimal calculateTotalInterest(BigDecimal loanAmount, int termMonths) {
        return calculateTotalAmountPayable(loanAmount, termMonths).subtract(loanAmount);
    }

    @Override
    public List<AmortizationInstallment> generateAmortizationSchedule(BigDecimal loanAmount, int termMonths) {

        int months = termMonths;

        BigDecimal balance = loanAmount;
        BigDecimal monthlyInstallment = calculateMonthlyInstallment(loanAmount, termMonths);
        BigDecimal endingBalance = BigDecimal.ZERO;

        List<AmortizationInstallment> amortizationSchedule = new ArrayList<>();

        for (int i = 0; i < months; i++) {

            BigDecimal interest = balance.multiply(MONTHLY_INTEREST_RATE).setScale(2,
                    RoundingMode.HALF_UP);

            BigDecimal amortization = monthlyInstallment.subtract(interest).setScale(2, RoundingMode.HALF_UP);

            if (i == months - 1) {
                endingBalance = BigDecimal.ZERO;
            } else {
                endingBalance = balance.subtract(amortization);
            }

            AmortizationInstallment row = new AmortizationInstallment();

            row.setInstallmentNumber(i + 1);
            row.setInitialBalance(balance);
            row.setInterest(interest);
            row.setAmortization(amortization);
            row.setInstallmentAmount(monthlyInstallment);
            row.setFinalBalance(endingBalance);

            balance = endingBalance;

            amortizationSchedule .add(row);
        }
        return amortizationSchedule;
    }

    /*
     * public BigDecimal[][] interesMensualTabla(SimuladorPrestamoRequestDto datos)
     * {
     * 
     * int meses = datos.plazo();
     * 
     * AmortizacionFrancesa tabla = new AmortizacionFrancesa();
     * 
     * tabla.setSaldoInicial(datos.monto());
     * 
     * BigDecimal amortz = BigDecimal.ZERO;
     * BigDecimal cuota = calcularCuotaMensual(datos);
     * 
     * BigDecimal[][] amortizacion = new BigDecimal[meses][6];
     * 
     * for (int i = 0; i < meses; i++) {
     * if (i <= meses) {
     * 
     * amortizacion[i][0] = BigDecimal.valueOf(i + 1);
     * 
     * amortizacion[i][1] = tabla.getSaldoInicial();
     * 
     * tabla.setInteres(tabla.getSaldoInicial().multiply(BigDecimal.valueOf(
     * INTERES_BASE_MENSUAL)).setScale(2,
     * RoundingMode.HALF_UP));
     * 
     * amortizacion[i][2] = tabla.getInteres();
     * 
     * amortz = calcularCuotaMensual(datos).subtract(tabla.getInteres()).setScale(0,
     * RoundingMode.HALF_UP);
     * 
     * amortizacion[i][3] = amortz;
     * 
     * amortizacion[i][4] = cuota;
     * 
     * tabla.setSaldoFinal(tabla.getSaldoInicial().subtract(amortz));
     * amortizacion[i][5] = tabla.getSaldoFinal();
     * 
     * tabla.setSaldoInicial(tabla.getSaldoFinal());
     * }
     * }
     * 
     * return amortizacion;
     * }
     */

    /*
     * // I = c * r * t
     * public BigDecimal calcularInteresSimple(SimuladorPrestamoDto datos) {
     * 
     * BigDecimal tiempoAnual =
     * BigDecimal.valueOf(datos.plazo()).divide(BigDecimal.valueOf(12), 2,
     * RoundingMode.HALF_UP);
     * 
     * BigDecimal interesAnual =
     * BigDecimal.valueOf(INTERES_BASE_MENSUAL).multiply(BigDecimal.valueOf(12));
     * 
     * 
     * return datos.monto().multiply(interesAnual)
     * .multiply(tiempoAnual);
     * }
     */
}
