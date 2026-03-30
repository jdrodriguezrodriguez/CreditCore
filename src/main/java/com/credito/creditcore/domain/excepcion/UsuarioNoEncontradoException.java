package com.credito.creditcore.domain.excepcion;

public class UsuarioNoEncontradoException extends RuntimeException{
    
    public UsuarioNoEncontradoException(String mensaje){
        super(mensaje);
    }

    public UsuarioNoEncontradoException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
} 
