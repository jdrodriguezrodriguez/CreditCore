package com.credito.creditcore.application.person.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.person.port.in.GetPersonUseCase;
import com.credito.creditcore.domain.excepcion.PersonNotFoundException;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.port.PersonRepositoryPort;

@Service
public class GetPersonService implements GetPersonUseCase {

    private final PersonRepositoryPort personRepositoryPort;

    public GetPersonService(PersonRepositoryPort personRepositoryPort) {
        this.personRepositoryPort = personRepositoryPort;
    }

    @Override
    public Person getPerson(String documentNumber) {

        if (documentNumber == null) {
            throw new IllegalArgumentException("Document number is required.");
        }

        if (documentNumber.length() < 7 || documentNumber.length() > 10) {
            throw new IllegalArgumentException("Invalid document number.");
        }

        return personRepositoryPort.findByDocumentNumber(documentNumber)
                .orElseThrow(() ->
                        new PersonNotFoundException(
                                "Person not found with document number: " + documentNumber));
    }
}