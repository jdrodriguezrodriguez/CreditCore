package com.credito.creditcore.application.usuario.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.credito.creditcore.application.usuario.port.in.UpdateUserUseCase;
import com.credito.creditcore.domain.model.User;
import com.credito.creditcore.domain.port.UserRepositoryPort;

@Service
public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public UpdateUserService(
            UserRepositoryPort userRepositoryPort,
            PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void updateUser(
            Integer userId,
            String username,
            String password) {

        Optional<User> user = userRepositoryPort.findByUsername(username);

        if (user.isPresent()) {

            if (user.get().getUserId().equals(userId)) {
                throw new IllegalArgumentException(
                        "You already have this username.");
            }

            throw new IllegalArgumentException(
                    "Username is already registered.");
        }

        if (password.length() != 4) {
            throw new IllegalArgumentException(
                    "Password must contain exactly 4 characters.");
        }

        String encodedPassword = passwordEncoder.encode(password);

        userRepositoryPort.update(
                userId,
                username,
                encodedPassword);
    }
}