package com.credito.creditcore.application.prestamo.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.prestamo.port.AmortizacionFrancesaService;
import com.credito.creditcore.domain.model.score.CuotaAmortizacion;

@Service
public class AmortizacionFrancesaServiceImpl implements AmortizacionFrancesaService{

    // private final double INTERES_BASE_MENSUAL = 0.02;

    // TEST
    private final double INTERES_BASE_MENSUAL = 0.045;

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
    public BigDecimal calcularCuotaMensual(BigDecimal monto, int plazo) {

        BigDecimal tasa = BigDecimal.valueOf(INTERES_BASE_MENSUAL);
        BigDecimal UnoMasTasa = BigDecimal.valueOf(1).add(tasa);
        BigDecimal potencia = UnoMasTasa.pow(plazo);

        BigDecimal numerador = tasa.multiply(potencia);
        BigDecimal denominador = potencia.subtract(BigDecimal.valueOf(1));

        BigDecimal cuotaMensual = monto.multiply(numerador).divide(denominador, 2, RoundingMode.HALF_UP);

        return cuotaMensual;
    }

    @Override
    public BigDecimal calcularTotalPagar(BigDecimal monto, int plazo) {
        return calcularCuotaMensual(monto, plazo).multiply(BigDecimal.valueOf(plazo));
    }

    // I = t - P
     @Override
    public BigDecimal calcularInteresTotal(BigDecimal monto, int plazo) {
        return calcularTotalPagar(monto, plazo).subtract(monto);
    }

    @Override
    public List<CuotaAmortizacion> generarTablaAmortizacion(BigDecimal monto, int plazo) {

        int meses = plazo;

        BigDecimal saldo = monto;
        BigDecimal cuotaMensual = calcularCuotaMensual(monto, plazo);
        BigDecimal saldoFinal = BigDecimal.ZERO;

        List<CuotaAmortizacion> tabla = new ArrayList<>();

        for (int i = 0; i < meses; i++) {

            BigDecimal interes = saldo.multiply(BigDecimal.valueOf(INTERES_BASE_MENSUAL)).setScale(2,
                    RoundingMode.HALF_UP);

            BigDecimal amortz = cuotaMensual.subtract(interes).setScale(2, RoundingMode.HALF_UP);

            if (i == meses - 1) {
                saldoFinal = BigDecimal.ZERO;
            }else{
                saldoFinal = saldo.subtract(amortz);
            }
            
            CuotaAmortizacion fila = new CuotaAmortizacion();

            fila.setCuota(i + 1);
            fila.setSaldoInicial(saldo);
            fila.setInteres(interes);
            fila.setAmortz(amortz);
            fila.setMontoCuota(cuotaMensual);
            fila.setSaldoFinal(saldoFinal);

            saldo = saldoFinal;

            tabla.add(fila);
        }
        return tabla;
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
