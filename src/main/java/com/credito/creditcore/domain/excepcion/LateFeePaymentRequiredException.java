package com.credito.creditcore.domain.excepcion;

public class LateFeePaymentRequiredException extends RuntimeException{

    public LateFeePaymentRequiredException(String message){
        super(message);
    }

    public LateFeePaymentRequiredException(String message, Throwable cause){
        super(message, cause);
    }
}
