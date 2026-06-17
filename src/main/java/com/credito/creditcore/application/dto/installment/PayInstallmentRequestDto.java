package com.credito.creditcore.application.dto.installment;

import java.math.BigDecimal;

import com.credito.creditcore.domain.model.enums.PaymentMethod;

import jakarta.validation.constraints.NotBlank;

public record PayInstallmentRequestDto(

        @NotBlank(message = "El monto a pagar es obligatorio")
        BigDecimal amountToPay,

        @NotBlank(message = "La forma de pago es obligatoria")
        PaymentMethod paymentMethod
) {                         
}
