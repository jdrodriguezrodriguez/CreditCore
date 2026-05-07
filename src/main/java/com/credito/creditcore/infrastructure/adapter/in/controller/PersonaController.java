package com.credito.creditcore.infrastructure.adapter.in.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.dto.PersonaDto;
import com.credito.creditcore.application.dto.RegistrarPersonaCommand;
import com.credito.creditcore.application.persona.port.in.ActualizarPersonaUseCase;
import com.credito.creditcore.application.persona.port.in.EliminarPersonaUseCase;
import com.credito.creditcore.application.persona.port.in.ObtenerPersonaUseCase;
import com.credito.creditcore.application.persona.port.in.RegistrarPersonaUseCase;
import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.infrastructure.adapter.in.mapper.PersonaMapperIn;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/credito/personas")
public class PersonaController {

    private final RegistrarPersonaUseCase registrarPersonaUseCase;
    private final ObtenerPersonaUseCase obtenerPersonaUseCase;
    private final ActualizarPersonaUseCase actualizarPersonaUseCase;
    private final EliminarPersonaUseCase eliminarPersonaUseCase;

    public PersonaController(RegistrarPersonaUseCase registrarPersonaUseCase,
            ObtenerPersonaUseCase obtenerPersonaUseCase, ActualizarPersonaUseCase actualizarPersonaUseCase,
            EliminarPersonaUseCase eliminarPersonaUseCase) {
        this.registrarPersonaUseCase = registrarPersonaUseCase;
        this.obtenerPersonaUseCase = obtenerPersonaUseCase;
        this.actualizarPersonaUseCase = actualizarPersonaUseCase;
        this.eliminarPersonaUseCase = eliminarPersonaUseCase;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPersona(@Valid @RequestBody RegistrarPersonaCommand datos) {

        Persona persona = PersonaMapperIn.crearModelo(datos.persona());

        registrarPersonaUseCase.registrarPersona(persona, datos.usuario().password());

        return ResponseEntity.ok(Map.of("Mensaje", "Registro exitoso."));
    }

    @PutMapping("/{documento}")
    public ResponseEntity<?> actualizarPersona(@Valid @RequestBody PersonaDto personaDto,
            @PathVariable String documento) {
        Persona persona = PersonaMapperIn.crearModelo(personaDto);
        actualizarPersonaUseCase.actualizarPersona(documento, persona);
        return ResponseEntity.ok(Map.of("Mensaje", "Actualizacion exitosa."));
    }

    @DeleteMapping("/{documento}")
    public ResponseEntity<?> eliminarPersona(@PathVariable String documento) {
        eliminarPersonaUseCase.eliminarPersona(documento);
        return ResponseEntity.ok(Map.of("Mensaje", "Se elimino a la persona."));
    }

    @GetMapping("/{documento}")
    public ResponseEntity<?> buscarPersona(@PathVariable String documento) {
        Persona persona = obtenerPersonaUseCase.obtenerPersona(documento);

        PersonaDto datos = PersonaMapperIn.crearDto(persona);

        return ResponseEntity.ok(datos);
    }
}
