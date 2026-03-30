package com.credito.creditcore.domain.excepcion;

public class PersonaNoEncontradaException extends RuntimeException{
    
    public PersonaNoEncontradaException(String mensaje){
        super(mensaje);
    }

    public PersonaNoEncontradaException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
