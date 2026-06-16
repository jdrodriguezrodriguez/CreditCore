package com.credito.creditcore.application.person.port.in;

import com.credito.creditcore.domain.model.Person;

public interface GetPersonUseCase {
    Person getPerson(String documentNumber);
}
