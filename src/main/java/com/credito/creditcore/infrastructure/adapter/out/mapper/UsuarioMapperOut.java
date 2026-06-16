package com.credito.creditcore.infrastructure.adapter.out.mapper;


import com.credito.creditcore.application.dto.usuario.UsuarioDto;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.model.User;
import com.credito.creditcore.domain.model.enums.UserRole;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.entity.UsuarioEntity;

public class UsuarioMapperOut {

    public static UsuarioEntity crearEntidad(User usuario, PersonaEntity personaEntity) {
        return new UsuarioEntity(
                personaEntity,
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getRolUsuario(),
                usuario.is_enabled(),
                usuario.isAccount_no_expired(),
                usuario.isAccount_no_locked(),
                usuario.isCredential_no_expired());
    }

    public static User crearModelo(UsuarioDto usuarioDto) {
        User usuario = new User();
        usuario.setRolUsuario(UserRole.CLIENTE);
        usuario.setPassword(usuarioDto.password());

        return usuario;
    }

    public static User toDomain(UsuarioEntity uEntity, Person persona) {
        return new User(
                uEntity.getIdUsuario(),
                persona,
                uEntity.getUsername(),
                uEntity.getPassword(),
                uEntity.getRolUsuario(),
                uEntity.is_enabled(),
                uEntity.isAccount_no_expired(),
                uEntity.isAccount_no_locked(),
                uEntity.isCredential_no_expired());
    }
}
