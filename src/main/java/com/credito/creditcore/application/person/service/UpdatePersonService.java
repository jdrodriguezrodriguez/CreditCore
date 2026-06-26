package com.credito.creditcore.application.person.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.person.port.in.UpdatePersonUseCase;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.port.PersonRepositoryPort;

@Service
public class UpdatePersonService implements UpdatePersonUseCase {

    private final PersonRepositoryPort personRepositoryPort;

    public UpdatePersonService(PersonRepositoryPort personRepositoryPort) {
        this.personRepositoryPort = personRepositoryPort;
    }

    @Override
    public void updatePerson(String documentNumber, Person person) {

        if (documentNumber == null) {
            throw new IllegalArgumentException("Document number is required.");
        }

        if (documentNumber.length() < 7 || documentNumber.length() > 10) {
            throw new IllegalArgumentException("Invalid document number.");
        }

        if (!documentNumber.equals(person.getDocumentNumber())) {
            throw new IllegalArgumentException(
                    "Document number cannot be updated.");
        }

        personRepositoryPort.update(documentNumber, person);
    }
}
