package com.credito.creditcore.application.person.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.person.port.in.DeletePersonUseCase;
import com.credito.creditcore.domain.port.PersonRepositoryPort;

@Service
public class DeletePersonService implements DeletePersonUseCase {

    private PersonRepositoryPort personRepositoryPort;

    public DeletePersonService(PersonRepositoryPort personRepositoryPort) {
        this.personRepositoryPort = personRepositoryPort;
    }

    @Override
    public void deletePerson(String documentNumber) {

        if (documentNumber == null) {
            throw new IllegalArgumentException("Document number is required.");
        }

        if (documentNumber.length() < 7 || documentNumber.length() > 10) {
            throw new IllegalArgumentException("Invalid document number.");
        }

        personRepositoryPort.delete(documentNumber);
    }
}
