package com.credito.creditcore.infrastructure.adapter.in.handler;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.domain.excepcion.CustomerNotFoundException;
import com.credito.creditcore.domain.excepcion.PersonNotFoundException;
import com.credito.creditcore.domain.excepcion.UsuarioNoEncontradoException;

@RestController
public class GlobalExceptionHandler {
    
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlePersonaNoEncontrada(PersonNotFoundException ex){

        Map<String, String> errores = new HashMap<>();

        errores.put("error", "Persona no encontrada");
        errores.put("detalle", ex.getMessage());

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<Map<String,String>> handleUsuarioNoEncontrado(UsuarioNoEncontradoException ex){

        Map<String, String> errores = new HashMap<>();

        errores.put("error", "Usuario no encontrado ");
        errores.put("detalle", ex.getMessage());

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleClienteNoEncontrado(CustomerNotFoundException ex){
        Map<String, String> errores = new HashMap<>();

        errores.put("error", "Cliente no encontrado ");
        errores.put("detalle", ex.getMessage());

        return ResponseEntity.badRequest().body(errores);
    }
}
