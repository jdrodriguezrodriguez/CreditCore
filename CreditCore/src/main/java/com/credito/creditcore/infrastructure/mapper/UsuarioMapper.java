package com.credito.creditcore.infrastructure.mapper;

import com.credito.creditcore.domain.model.Usuario;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.entity.UsuarioEntity;

public class UsuarioMapper {
    
    public static UsuarioEntity crearEntidad(Usuario usuario, PersonaEntity personaEntity){
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
}
