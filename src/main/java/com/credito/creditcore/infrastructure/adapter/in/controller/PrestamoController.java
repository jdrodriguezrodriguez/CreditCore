package com.credito.creditcore.infrastructure.adapter.in.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.dto.prestamo.CrearPrestamoRequestDto;
import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoRequestDto;
import com.credito.creditcore.application.prestamo.port.CrearPrestamoUseCase;
import com.credito.creditcore.application.prestamo.port.SimularPrestamoUseCase;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/credito/prestamo")
public class PrestamoController {

    private final SimularPrestamoUseCase simularPrestamoUseCase;
    private final CrearPrestamoUseCase crearPrestamoUseCase;

    public PrestamoController(SimularPrestamoUseCase simularPrestamoUseCase,
            CrearPrestamoUseCase crearPrestamoUseCase) {
        this.simularPrestamoUseCase = simularPrestamoUseCase;
        this.crearPrestamoUseCase = crearPrestamoUseCase;
    }

    @GetMapping("/simulador/{idPersona}")
    public ResponseEntity<?> simulacionPrestamo(@PathVariable int idPersona,
            @RequestBody SimuladorPrestamoRequestDto datos) {
        return ResponseEntity.ok(simularPrestamoUseCase.simularPrestamo(datos, idPersona));
    }

    @PostMapping("/crear/{idPersona}")
    public ResponseEntity<?> crearPrestamo(@RequestBody CrearPrestamoRequestDto datos, @PathVariable int idPersona) {
        crearPrestamoUseCase.crearPrestamo(datos, idPersona);
        return ResponseEntity.ok(Map.of("Mensaje", "Se ha registrado el prestamo"));
    }

}
