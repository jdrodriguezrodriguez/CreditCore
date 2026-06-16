package com.credito.creditcore.domain.excepcion;

public class PersonNotFoundException extends RuntimeException{
    
    public PersonNotFoundException(String mensaje){
        super(mensaje);
    }

    public PersonNotFoundException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
