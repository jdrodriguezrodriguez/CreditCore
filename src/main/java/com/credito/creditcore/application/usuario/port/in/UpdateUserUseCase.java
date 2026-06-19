package com.credito.creditcore.application.usuario.port.in;

public interface UpdateUserUseCase {
    void updateUser(Integer userId, String username, String password);
}
