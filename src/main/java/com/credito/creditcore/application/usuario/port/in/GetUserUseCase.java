package com.credito.creditcore.application.usuario.port.in;

import com.credito.creditcore.domain.model.User;

public interface GetUserUseCase {
    User getUser(Integer userId);
}
