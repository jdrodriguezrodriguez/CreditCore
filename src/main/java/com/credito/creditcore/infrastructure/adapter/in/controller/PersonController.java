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

import com.credito.creditcore.application.person.port.in.UpdatePersonUseCase;
import com.credito.creditcore.application.dto.person.PersonDto;
import com.credito.creditcore.application.dto.person.RegisterPersonCommand;
import com.credito.creditcore.application.person.port.in.DeletePersonUseCase;
import com.credito.creditcore.application.person.port.in.GetPersonUseCase;
import com.credito.creditcore.application.person.port.in.RegisterPersonUseCase;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.infrastructure.adapter.in.mapper.PersonaMapperIn;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/credito/persons")
public class PersonController {

    private final RegisterPersonUseCase registerPersonUseCase;
    private final GetPersonUseCase getPersonUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;
    private final DeletePersonUseCase deletePersonUseCase;

    public PersonController(RegisterPersonUseCase registerPersonUseCase, GetPersonUseCase getPersonUseCase,
            UpdatePersonUseCase updatePersonUseCase, DeletePersonUseCase deletePersonUseCase) {
        this.registerPersonUseCase = registerPersonUseCase;
        this.getPersonUseCase = getPersonUseCase;
        this.updatePersonUseCase = updatePersonUseCase;
        this.deletePersonUseCase = deletePersonUseCase;
    }

    @PostMapping
    public ResponseEntity<?> registerPerson(@Valid @RequestBody RegisterPersonCommand request) {

        Person person = PersonaMapperIn.createModel(request.person());

        registerPersonUseCase.registerPerson(person, request.user().password());

        return ResponseEntity.ok(Map.of("message", "Person registered successfully."));
    }

    @PutMapping("/{documentNumber}")
    public ResponseEntity<?> updatePerson(@Valid @RequestBody PersonDto request,
            @PathVariable String documentNumber) {
                
        Person person = PersonaMapperIn.createModel(request);
        updatePersonUseCase.updatePerson(documentNumber, person);
        return ResponseEntity.ok(Map.of("message", "Person updated successfully."));
    }

    @DeleteMapping("/{documentNumber}")
    public ResponseEntity<?> deletePerson(@PathVariable String documentNumber) {
        deletePersonUseCase.deletePerson(documentNumber);
        return ResponseEntity.ok(Map.of("message", "Person deleted successfully."));
    }

    @GetMapping("/{documentNumber}")
    public ResponseEntity<?> getPerson(@PathVariable String documentNumber) {
        Person person = getPersonUseCase.getPerson(documentNumber);

        PersonDto response = PersonaMapperIn.createDto(person);

        return ResponseEntity.ok(response);
    }
}
