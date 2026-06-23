package com.credito.creditcore.infrastructure.adapter.in.handler;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.domain.excepcion.CustomerNotFoundException;
import com.credito.creditcore.domain.excepcion.PersonNotFoundException;
import com.credito.creditcore.domain.excepcion.UserNotFoundException;

@RestController
public class GlobalExceptionHandler {
    
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlePersonNotFound(PersonNotFoundException ex){

        Map<String, String> errors = new HashMap<>();

        errors.put("error", "Person not found ");
        errors.put("details", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFound(UserNotFoundException ex){

        Map<String, String> errors = new HashMap<>();

        errors.put("error", "Customer not found ");
        errors.put("details", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleCustomerNotFound(CustomerNotFoundException ex){
        Map<String, String> errors = new HashMap<>();

        errors.put("error", "Customer not found ");
        errors.put("details", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
}
