package com.credito.creditcore.domain.model.score;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class PuntajeEndeudamiento {
    BigDecimal limite;
    int puntaje;

    public PuntajeEndeudamiento(BigDecimal limite, int puntaje) {
        this.limite = limite;
        this.puntaje = puntaje;
    }

}
