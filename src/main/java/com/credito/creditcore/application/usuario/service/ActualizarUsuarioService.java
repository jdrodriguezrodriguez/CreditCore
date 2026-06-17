package com.credito.creditcore.application.usuario.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.credito.creditcore.application.usuario.port.in.ActualizarUsuarioUseCase;
import com.credito.creditcore.domain.model.User;
import com.credito.creditcore.domain.port.UserRepositoryPort;

@Service
public class ActualizarUsuarioService implements ActualizarUsuarioUseCase {

    private final UserRepositoryPort uRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public ActualizarUsuarioService(UserRepositoryPort uRepositoryPort, PasswordEncoder passwordEncoder) {
        this.uRepositoryPort = uRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void actualizarUsuario(Integer idUser, String username, String password) {

        Optional<User> usuario = uRepositoryPort.buscarPorUsername(username);

        if (usuario.isPresent()) {
            User u = usuario.get();

            if (u.getUserId().equals(idUser)) {
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
