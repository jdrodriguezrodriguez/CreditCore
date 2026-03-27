package com.credito.creditcore.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PersonaDto(
                @NotBlank(message = "El nombre es obligatorio")
                String nombre,

                @NotBlank(message = "El apellido es obligatorio")
                String apellido,

                @NotBlank(message = "El documento es obligatorio")
                @Size(min = 8, max = 11, message = "El documento debe tener entre 8 y 11 caracteres")
                String documento,

                @NotNull(message = "La fecha de nacimiento es obligatoria")
                String nacimiento,

                @NotBlank(message = "El correo es obligatorio")
                @Email(message = "Correo debe ser valido @")
                String correo
            ){
}
