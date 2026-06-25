package com.credito.creditcore.application.dto.installment;

import java.math.BigDecimal;

import com.credito.creditcore.domain.model.enums.PaymentMethod;

import jakarta.validation.constraints.NotBlank;

public record PayInstallmentRequestDto(

        @NotBlank(message = "The amount to pay is required.")
        BigDecimal amountToPay,

        @NotBlank(message = "The payment method is required.")
        PaymentMethod paymentMethod
) {                         
}
