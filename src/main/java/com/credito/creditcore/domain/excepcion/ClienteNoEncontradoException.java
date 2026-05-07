package com.credito.creditcore.domain.excepcion;

public class ClienteNoEncontradoException extends RuntimeException{
    
    public ClienteNoEncontradoException(String mensaje){
        super(mensaje);
    }

    public ClienteNoEncontradoException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
