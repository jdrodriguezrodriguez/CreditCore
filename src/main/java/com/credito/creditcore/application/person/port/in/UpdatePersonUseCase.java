package com.credito.creditcore.application.person.port.in;

import com.credito.creditcore.domain.model.Person;

public interface UpdatePersonUseCase {
    void updatePerson(String documentNumber, Person person);
}
