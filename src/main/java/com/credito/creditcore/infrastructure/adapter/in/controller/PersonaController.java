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

import com.credito.creditcore.application.dto.persona.PersonaDto;
import com.credito.creditcore.application.dto.persona.RegistrarPersonaCommand;
import com.credito.creditcore.application.person.port.in.UpdatePersonUseCase;
import com.credito.creditcore.application.person.port.in.DeletePersonUseCase;
import com.credito.creditcore.application.person.port.in.GetPersonUseCase;
import com.credito.creditcore.application.person.port.in.RegisterPersonUseCase;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.infrastructure.adapter.in.mapper.PersonaMapperIn;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/credito/personas")
public class PersonaController {

    private final RegisterPersonUseCase registrarPersonaUseCase;
    private final GetPersonUseCase obtenerPersonaUseCase;
    private final UpdatePersonUseCase actualizarPersonaUseCase;
    private final DeletePersonUseCase eliminarPersonaUseCase;

    public PersonaController(RegisterPersonUseCase registrarPersonaUseCase,
            GetPersonUseCase obtenerPersonaUseCase, UpdatePersonUseCase actualizarPersonaUseCase,
            DeletePersonUseCase eliminarPersonaUseCase) {
        this.registrarPersonaUseCase = registrarPersonaUseCase;
        this.obtenerPersonaUseCase = obtenerPersonaUseCase;
        this.actualizarPersonaUseCase = actualizarPersonaUseCase;
        this.eliminarPersonaUseCase = eliminarPersonaUseCase;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPersona(@Valid @RequestBody RegistrarPersonaCommand datos) {

        Person persona = PersonaMapperIn.crearModelo(datos.persona());

        registrarPersonaUseCase.registerPerson(persona, datos.usuario().password());

        return ResponseEntity.ok(Map.of("Mensaje", "Registro exitoso."));
    }

    @PutMapping("/{documento}")
    public ResponseEntity<?> actualizarPersona(@Valid @RequestBody PersonaDto personaDto,
            @PathVariable String documento) {
        Person persona = PersonaMapperIn.crearModelo(personaDto);
        actualizarPersonaUseCase.updatePerson(documento, persona);
        return ResponseEntity.ok(Map.of("Mensaje", "Actualizacion exitosa."));
    }

    @DeleteMapping("/{documento}")
    public ResponseEntity<?> eliminarPersona(@PathVariable String documento) {
        eliminarPersonaUseCase.deletePerson(documento);
        return ResponseEntity.ok(Map.of("Mensaje", "Se elimino a la persona."));
    }

    @GetMapping("/{documento}")
    public ResponseEntity<?> buscarPersona(@PathVariable String documento) {
        Person persona = obtenerPersonaUseCase.getPerson(documento);

        PersonaDto datos = PersonaMapperIn.crearDto(persona);

        return ResponseEntity.ok(datos);
    }
}
