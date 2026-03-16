package com.credito.creditcore.infrastructure.adapter.in;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.dto.RegistrarPersonaCommand;
import com.credito.creditcore.application.port.in.RegistrarPersonaUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/credito/personas")
public class PersonaController {

    private RegistrarPersonaUseCase registrarPersonaUseCase;

    public PersonaController(RegistrarPersonaUseCase registrarPersonaUseCase){
        this.registrarPersonaUseCase = registrarPersonaUseCase;
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPersona(@Valid @RequestBody RegistrarPersonaCommand datos){
        registrarPersonaUseCase.registrarPersona(datos);
        return ResponseEntity.ok().body(null);
    }
}
