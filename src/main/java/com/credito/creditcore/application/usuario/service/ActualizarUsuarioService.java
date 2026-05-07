package com.credito.creditcore.application.usuario.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.credito.creditcore.application.usuario.port.in.ActualizarUsuarioUseCase;
import com.credito.creditcore.domain.model.Usuario;
import com.credito.creditcore.domain.port.UsuarioRepositoryPort;

@Service
public class ActualizarUsuarioService implements ActualizarUsuarioUseCase {

    private final UsuarioRepositoryPort uRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public ActualizarUsuarioService(UsuarioRepositoryPort uRepositoryPort, PasswordEncoder passwordEncoder) {
        this.uRepositoryPort = uRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void actualizarUsuario(Integer idUser, String username, String password) {

        Optional<Usuario> usuario = uRepositoryPort.buscarPorUsername(username);

        if (usuario.isPresent()) {
            Usuario u = usuario.get();

            if (u.getIdUsuario().equals(idUser)) {
                throw new IllegalArgumentException("Ya tienes este username.");
            }
            throw new IllegalArgumentException("Usuario ya registrado con ese username.");
        }

        if (password.length() != 4) {
            throw new IllegalArgumentException("La contraseña debe tener 4 caracteres");
        }

        String passString = passwordEncoder.encode(password);
        uRepositoryPort.actualizar(idUser, username, passString);
    }
}
