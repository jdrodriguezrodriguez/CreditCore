package com.credito.creditcore.application.dto.person;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PersonDto(
                @NotBlank(message = "First name is required")
                String firstName,

                @NotBlank(message = "Last name is required")
                String lastName,

                @NotBlank(message = "Document number is required")
                @Size(min = 8, max = 11, message = "Document number must be between 8 and 11 characters")
                String documentNumber,

                @NotNull(message = "Birth date is required")
                String birthDate,

                @NotBlank(message = "Email is required")
                @Email(message = "Email must be a valid email address")
                String email
            ){
}
