package com.credito.creditcore.application.port.in;

import com.credito.creditcore.application.dto.RegistrarPersonaCommand;

public interface RegistrarPersonaUseCase {
    void registrarPersona(RegistrarPersonaCommand datos);
}
