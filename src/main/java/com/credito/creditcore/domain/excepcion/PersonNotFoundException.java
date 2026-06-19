package com.credito.creditcore.domain.excepcion;

public class PersonNotFoundException extends RuntimeException{
    
    public PersonNotFoundException(String message){
        super(message);
    }

    public PersonNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
