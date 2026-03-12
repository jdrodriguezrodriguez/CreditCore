package com.credito.creditcore.infrastructure.adapter.out;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Usuario;
import com.credito.creditcore.domain.port.UsuarioRepositoryPort;
import com.credito.creditcore.infrastructure.entity.UsuarioEntity;
import com.credito.creditcore.infrastructure.persistence.JpaUsuarioRepository;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort{

    private JpaUsuarioRepository usuarioRepositoryJpa;

    public UsuarioRepositoryAdapter(JpaUsuarioRepository usuarioRepositoryJpa){
        this.usuarioRepositoryJpa = usuarioRepositoryJpa;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setUsername(usuario.getUsername());
        usuarioEntity.setPassword(usuario.getPassword());

        UsuarioEntity guardado = usuarioRepositoryJpa.save(usuarioEntity);

        return new Usuario(null, null, null, null, null, false, false, false, false);
    }
    
}
