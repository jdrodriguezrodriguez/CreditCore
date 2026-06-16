package com.credito.creditcore.application.persona.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.credito.creditcore.application.persona.port.in.RegistrarPersonaUseCase;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.model.Usuario;
import com.credito.creditcore.domain.model.enums.RolUsuario;
import com.credito.creditcore.domain.port.PersonRepositoryPort;
import com.credito.creditcore.domain.port.UsuarioRepositoryPort;

@Service
public class RegistrarPersonaService implements RegistrarPersonaUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PersonRepositoryPort personaRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public RegistrarPersonaService(UsuarioRepositoryPort usuarioRepositoryPort,
            PersonRepositoryPort personaRepositoryPort,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.personaRepositoryPort = personaRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registrarPersona(Person persona, String password) {

        if (personaRepositoryPort.existePorDocumento(persona.getDocumento())) {
            throw new IllegalArgumentException("Persona ya registrada con ese documento.");
        }

        Person personaGuardada = personaRepositoryPort.guardar(persona);

        String passString = passwordEncoder.encode(password);

        Usuario nuevoUsuario = Usuario.crear(personaGuardada, RolUsuario.CLIENTE, passString);
        nuevoUsuario.generarUsername();

        usuarioRepositoryPort.guardar(nuevoUsuario);
        return;
    }
}
