package com.credito.creditcore.infrastructure.adapter.in.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.dto.installment.PayInstallmentRequestDto;
import com.credito.creditcore.application.installment.port.GetInstallmentsUseCase;
import com.credito.creditcore.application.installment.port.PayInstallmentUseCase;
import com.credito.creditcore.domain.model.Installment;

@RestController
@RequestMapping("/api/credito/cuotas")
public class CuotaController {
    
    private final GetInstallmentsUseCase getInstallmentsUseCase;
    private final PayInstallmentUseCase payInstallmentUseCase;

	public CuotaController(GetInstallmentsUseCase getInstallmentsUseCase, PayInstallmentUseCase payInstallmentUseCase) {
		this.getInstallmentsUseCase = getInstallmentsUseCase;
		this.payInstallmentUseCase = payInstallmentUseCase;
	}

    @GetMapping("/consultar/{idPrestamo}")
    public ResponseEntity<List<Installment>> obtenerCuotas(@PathVariable Integer idPrestamo){
        return ResponseEntity.ok(getInstallmentsUseCase.getInstallments(idPrestamo));
    }

    @PostMapping("/pagar/{idCuota}")
    public ResponseEntity<?> pagarCuota(@PathVariable Integer idCuota, @RequestBody PayInstallmentRequestDto datos){
        payInstallmentUseCase.payInstallment(idCuota, datos);
        return ResponseEntity.ok(Map.of("Mensaje", "Se pago la cuota de forma exitosa."));
    }
}
