package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.model.User;
import com.credito.creditcore.domain.port.UserRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.UsuarioMapperOut;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.entity.UsuarioEntity;
import com.credito.creditcore.infrastructure.persistence.PersonaRepositoryJpa;
import com.credito.creditcore.infrastructure.persistence.UsuarioRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class UsuarioRepositoryAdapter implements UserRepositoryPort {

    private final UsuarioRepositoryJpa usuarioRepositoryJpa;
    private final PersonaRepositoryJpa personaRepositoryJpa;

    public UsuarioRepositoryAdapter(UsuarioRepositoryJpa usuarioRepositoryJpa,
            PersonaRepositoryJpa personaRepositoryJpa) {
        this.usuarioRepositoryJpa = usuarioRepositoryJpa;
        this.personaRepositoryJpa = personaRepositoryJpa;
    }

    @Override
    public void save(User usuario) {

        PersonaEntity personaEntity = personaRepositoryJpa.findById(usuario.getPersona().getIdPersona())
                .orElseThrow(() -> new EntityNotFoundException());

        UsuarioEntity userEntity = UsuarioMapperOut.crearEntidad(usuario, personaEntity);

        usuarioRepositoryJpa.save(userEntity);
    }

    @Override
    public Optional<User> buscarPorUsername(String username) {

        return usuarioRepositoryJpa.findByUsername(username).map(
                t -> {
                    Person persona = new Person();
                    return UsuarioMapperOut.toDomain(t, persona);
                });
    }

    @Override
    public void actualizar(Integer idUser, String username, String password) {
        UsuarioEntity usuarioEntity = usuarioRepositoryJpa.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException());

        usuarioEntity.setUsername(username);
        usuarioEntity.setPassword(password);

        usuarioRepositoryJpa.save(usuarioEntity);
    }

    @Override
    public Optional<User> consultar(Integer idUser) {
        return usuarioRepositoryJpa.findById(idUser).map(
            usuario ->{
                Person persona = PersonaMapperOut.toDomain(usuario.getPersona());
                return UsuarioMapperOut.toDomain(usuario, persona);
            }
        );

    }
}
