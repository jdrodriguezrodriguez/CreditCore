package com.credito.creditcore.application.user.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.user.port.in.GetUserUseCase;
import com.credito.creditcore.domain.excepcion.UserNotFoundException;
import com.credito.creditcore.domain.model.User;
import com.credito.creditcore.domain.port.UserRepositoryPort;

@Service
public class GetUserService implements GetUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public GetUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User getUser(Integer userId) {
        return userRepositoryPort.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found with ID: " + userId));
    }
}