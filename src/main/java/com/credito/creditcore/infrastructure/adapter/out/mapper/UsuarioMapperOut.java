package com.credito.creditcore.infrastructure.adapter.out.mapper;


import com.credito.creditcore.application.dto.UsuarioDto;
import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.domain.model.Usuario;
import com.credito.creditcore.domain.model.enums.RolUsuario;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.entity.UsuarioEntity;

public class UsuarioMapperOut {

    public static UsuarioEntity crearEntidad(Usuario usuario, PersonaEntity personaEntity) {
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

    public static Usuario crearModelo(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setRolUsuario(RolUsuario.CLIENTE);
        usuario.setPassword(usuarioDto.password());

        return usuario;
    }

    public static Usuario toDomain(UsuarioEntity uEntity, Persona persona) {
        return new Usuario(
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
