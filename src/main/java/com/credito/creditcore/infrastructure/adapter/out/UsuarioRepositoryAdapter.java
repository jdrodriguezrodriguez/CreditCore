package com.credito.creditcore.infrastructure.adapter.out;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.domain.model.Usuario;
import com.credito.creditcore.domain.port.UsuarioRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
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

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {

        return usuarioRepositoryJpa.findByUsername(username).map(
                t -> {
                    Persona persona = new Persona();
                    return UsuarioMapperOut.toDomain(t, persona);
                });
    }

    @Override
    public void actualizar(Integer idUser, String username, String password) {
        UsuarioEntity usuarioEntity = usuarioRepositoryJpa.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Error accediendo a datos"));

        usuarioEntity.setUsername(username);
        usuarioEntity.setPassword(password);

        usuarioRepositoryJpa.save(usuarioEntity);
    }

    @Override
    public Optional<Usuario> consultar(Integer idUser) {
        return usuarioRepositoryJpa.findById(idUser).map(
            usuario ->{
                Persona persona = PersonaMapperOut.toDomain(usuario.getPersona());
                return UsuarioMapperOut.toDomain(usuario, persona);
            }
        );

    }
}
