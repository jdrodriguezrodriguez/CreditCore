package com.credito.creditcore.application.dto.cuota;

import java.math.BigDecimal;

import com.credito.creditcore.domain.model.enums.Fpago;

import jakarta.validation.constraints.NotBlank;

public record PagarCuotaRequestDto(

        @NotBlank(message = "El monto a pagar es obligatorio")
        BigDecimal montoPagar,

        @NotBlank(message = "La forma de pago es obligatoria")
        Fpago fpago
) {                         
}
