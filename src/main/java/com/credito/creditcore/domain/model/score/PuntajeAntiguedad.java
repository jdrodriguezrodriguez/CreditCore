package com.credito.creditcore.domain.model.score;

import lombok.Getter;

@Getter
public class PuntajeAntiguedad {
    
    long antiguedad;
    int puntaje;

    public PuntajeAntiguedad(long antiguedad, int puntaje) {
        this.antiguedad = antiguedad;
        this.puntaje = puntaje;
    }
}
