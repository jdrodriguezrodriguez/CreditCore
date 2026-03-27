package com.credito.creditcore.application.usuario.port.in;

import com.credito.creditcore.domain.model.Usuario;

public interface ObtenerUsuarioUseCase {
    Usuario buscarUsuario(Integer idUser);
}
