package com.credito.creditcore.infrastructure.adapter.in.mapper;

import java.time.LocalDate;

import com.credito.creditcore.application.dto.person.PersonDto;
import com.credito.creditcore.domain.model.Person;

public class PersonaMapperIn {

    public static Person createModel(PersonDto data) {
        return Person.create(
            data.firstName(), 
            data.lastName(), 
            data.documentNumber(), 
            LocalDate.parse(data.birthDate()), 
            data.email());
    }

    public static PersonDto createDto(Person person){
       return new PersonDto(
            person.getFirstName(), 
            person.getLastName(), 
            person.getDocumentNumber(),
            person.getBirthDate().toString(), 
            person.getEmail()
        );
    }
    
}
