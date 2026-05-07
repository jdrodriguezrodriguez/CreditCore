package com.credito.creditcore.infrastructure.adapter.in.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.prestamo.port.SimularPrestamoUseCase;

@RestController
@RequestMapping("/api/credito/prestamo")
public class PrestamoController {

    private final SimularPrestamoUseCase simularPrestamoUseCase;

    public PrestamoController(SimularPrestamoUseCase simularPrestamoUseCase){
        this.simularPrestamoUseCase = simularPrestamoUseCase;
    }

    public ResponseEntity<?> getSimularPrestamoUseCase() {
        return ResponseEntity.ok(null);
    }
    
}
