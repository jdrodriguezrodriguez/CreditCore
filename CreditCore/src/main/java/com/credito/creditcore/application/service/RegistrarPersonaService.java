package com.credito.creditcore.application.service;

import com.credito.creditcore.application.dto.RegistrarPersonaCommand;
import com.credito.creditcore.application.port.in.RegistrarPersonaUseCase;
import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.domain.model.Usuario;
import com.credito.creditcore.domain.model.enums.RolUsuario;
import com.credito.creditcore.domain.port.PersonaRepositoryPort;
import com.credito.creditcore.domain.port.UsuarioRepositoryPort;

public class RegistrarPersonaService implements RegistrarPersonaUseCase{

    private UsuarioRepositoryPort usuarioRepositoryPort;
    private PersonaRepositoryPort personaRepositoryPort;
    
    public RegistrarPersonaService(UsuarioRepositoryPort usuarioRepositoryPort, PersonaRepositoryPort personaRepositoryPort){
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.personaRepositoryPort = personaRepositoryPort;
    }

    @Override
    public void registrarPersona(RegistrarPersonaCommand datos) {
        Persona persona = Persona.crear(datos.persona());

        Persona personaGuardada = personaRepositoryPort.guardar(persona);

        Usuario usuario = Usuario.crear(personaGuardada, RolUsuario.CLIENTE, datos.usuario().password());
        usuario.generarUsername();

        usuarioRepositoryPort.guardar(usuario);
        return;
    }
}
