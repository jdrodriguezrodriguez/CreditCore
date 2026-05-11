package com.credito.creditcore.domain.model.score;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class PuntajeCapacidad {
    
    BigDecimal salario;
    int puntaje;

    public PuntajeCapacidad(BigDecimal salario, int puntaje) {
        this.salario = salario;
        this.puntaje = puntaje;
    }
}
