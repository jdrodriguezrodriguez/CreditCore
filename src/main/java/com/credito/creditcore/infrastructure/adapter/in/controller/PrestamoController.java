package com.credito.creditcore.infrastructure.adapter.in.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoRequestDto;
import com.credito.creditcore.application.prestamo.port.SimularPrestamoUseCase;

@RestController
@RequestMapping("/api/credito/prestamo")
public class PrestamoController {

    private final SimularPrestamoUseCase simularPrestamoUseCase;

    public PrestamoController(SimularPrestamoUseCase simularPrestamoUseCase){
        this.simularPrestamoUseCase = simularPrestamoUseCase;
    }

    @GetMapping("/simulador/{idPersona}")
    public ResponseEntity<?> simulacionPrestamo(@PathVariable int idPersona, @RequestBody SimuladorPrestamoRequestDto datos) {
        return ResponseEntity.ok(simularPrestamoUseCase.simularPrestamo(datos, idPersona));
    }
}
