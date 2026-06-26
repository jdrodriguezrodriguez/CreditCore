package com.credito.creditcore.application.dto.customer;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record CustomerSalaryDto(

        @NotBlank(message = "Salary is required.")
        BigDecimal salary
) {
}
