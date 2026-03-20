package com.credito.creditcore.infrastructure.adapter.out;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Usuario;
import com.credito.creditcore.domain.port.UsuarioRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.UsuarioMapperOut;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.entity.UsuarioEntity;
import com.credito.creditcore.infrastructure.persistence.PersonaRepositoryJpa;
import com.credito.creditcore.infrastructure.persistence.UsuarioRepositoryJpa;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioRepositoryJpa usuarioRepositoryJpa;
    private final PersonaRepositoryJpa personaRepositoryJpa;

    public UsuarioRepositoryAdapter(UsuarioRepositoryJpa usuarioRepositoryJpa,
            PersonaRepositoryJpa personaRepositoryJpa) {
        this.usuarioRepositoryJpa = usuarioRepositoryJpa;
        this.personaRepositoryJpa = personaRepositoryJpa;
    }

    @Override
    public void guardar(Usuario usuario) {

        PersonaEntity personaEntity = personaRepositoryJpa.findById(usuario.getPersona().getIdPersona())
            .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        UsuarioEntity userEntity = UsuarioMapperOut.crearEntidad(usuario, personaEntity);

        usuarioRepositoryJpa.save(userEntity);
    }

}
