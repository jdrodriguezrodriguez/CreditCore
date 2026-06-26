package com.credito.creditcore.infrastructure.adapter.in.mapper;

import com.credito.creditcore.application.dto.user.UpdateUserDto;
import com.credito.creditcore.domain.model.User;

public class UsuarioMapperIn {
    
    public static User crearModelo(UpdateUserDto datos){
        return new User();
    }
}
