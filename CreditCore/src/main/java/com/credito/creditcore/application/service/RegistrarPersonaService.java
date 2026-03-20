package com.credito.creditcore.application.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.port.in.RegistrarPersonaUseCase;
import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.domain.model.Usuario;
import com.credito.creditcore.domain.model.enums.RolUsuario;
import com.credito.creditcore.domain.port.PersonaRepositoryPort;
import com.credito.creditcore.domain.port.UsuarioRepositoryPort;

@Service
public class RegistrarPersonaService implements RegistrarPersonaUseCase{

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PersonaRepositoryPort personaRepositoryPort;
    
    public RegistrarPersonaService(UsuarioRepositoryPort usuarioRepositoryPort, PersonaRepositoryPort personaRepositoryPort){
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.personaRepositoryPort = personaRepositoryPort;
    }

    @Override
    public void registrarPersona(Persona persona, String password) {

        Persona personaGuardada = personaRepositoryPort.guardar(persona);

        Usuario nuevoUsuario = Usuario.crear(personaGuardada, RolUsuario.CLIENTE, password);
        nuevoUsuario.generarUsername();

        usuarioRepositoryPort.guardar(nuevoUsuario);
        return;
    }
}
