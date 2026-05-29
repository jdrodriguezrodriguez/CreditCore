package com.credito.creditcore.application.cuota.port;

import com.credito.creditcore.application.dto.cuota.PagarCuotaRequestDto;

public interface PagarCuotaUseCase {
    void pagarCuota(Integer cuotas, PagarCuotaRequestDto datos);
}
