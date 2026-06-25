package com.credito.creditcore.application.user.port.in;

public interface UpdateUserUseCase {
    void updateUser(Integer userId, String username, String password);
}
