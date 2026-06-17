package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.application.dto.usuario.UsuarioDto;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.model.User;
import com.credito.creditcore.domain.model.enums.UserRole;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.entity.UsuarioEntity;

public class UsuarioMapperOut {

    public static UsuarioEntity toEntity(User user, PersonaEntity personaEntity) {
        return new UsuarioEntity(
                personaEntity,
                user.getUsername(),
                user.getPassword(),
                user.getUserRole(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired()
        );
    }

    public static User toDomain(UsuarioEntity entity, Person person) {
        return new User(
                entity.getIdUsuario(),
                person,
                entity.getUsername(),
                entity.getPassword(),
                entity.getRolUsuario(),
                entity.is_enabled(),
                entity.isAccount_no_expired(),
                entity.isAccount_no_locked(),
                entity.isCredential_no_expired()
        );
    }

    public static User fromDto(UsuarioDto dto) {
        User user = new User();
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(dto.password());

        return user;
    }
}