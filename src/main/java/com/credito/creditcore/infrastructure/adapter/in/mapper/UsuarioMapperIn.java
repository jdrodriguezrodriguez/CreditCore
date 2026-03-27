package com.credito.creditcore.infrastructure.adapter.in.mapper;

import com.credito.creditcore.application.dto.UpdateUsuarioDto;
import com.credito.creditcore.domain.model.Usuario;

public class UsuarioMapperIn {
    
    public static Usuario crearModelo(UpdateUsuarioDto datos){
        return new Usuario();
    }
}
