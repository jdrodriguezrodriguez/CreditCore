package com.credito.creditcore.infrastructure.adapter.in.mapper;

import com.credito.creditcore.application.dto.usuario.UpdateUsuarioDto;
import com.credito.creditcore.domain.model.User;

public class UsuarioMapperIn {
    
    public static User crearModelo(UpdateUsuarioDto datos){
        return new User();
    }
}
