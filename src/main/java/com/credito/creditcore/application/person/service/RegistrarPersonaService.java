package com.credito.creditcore.application.person.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.credito.creditcore.application.person.port.in.RegisterPersonUseCase;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.model.User;
import com.credito.creditcore.domain.model.enums.UserRole;
import com.credito.creditcore.domain.port.PersonRepositoryPort;
import com.credito.creditcore.domain.port.UserRepositoryPort;

@Service
public class RegistrarPersonaService implements RegisterPersonUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PersonRepositoryPort personRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public RegistrarPersonaService(UserRepositoryPort usuarioRepositoryPort,
            PersonRepositoryPort personaRepositoryPort,
            PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = usuarioRepositoryPort;
        this.personRepositoryPort = personaRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerPerson(Person person, String password) {

        if (personRepositoryPort.existsByDocumentNumber(person.getDocumentNumber())) {
            throw new IllegalArgumentException(
                    "A person with this document number is already registered.");
        }

        Person savedPerson = personRepositoryPort.save(person);

        String encodedPassword = passwordEncoder.encode(password);

        User newUser = User.create(
                savedPerson,
                UserRole.CUSTOMER,
                encodedPassword);

        newUser.generateUsername();

        userRepositoryPort.save(newUser);
    }
}
