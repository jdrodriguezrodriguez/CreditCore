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

import com.credito.creditcore.application.cuota.port.ObtenerCuotasUseCase;
import com.credito.creditcore.application.cuota.port.PagarCuotaUseCase;
import com.credito.creditcore.application.dto.cuota.PagarCuotaRequestDto;
import com.credito.creditcore.domain.model.Cuota;

@RestController
@RequestMapping("/api/credito/cuotas")
public class CuotaController {
    
    private final ObtenerCuotasUseCase obtenerCuotasUseCase;
    private final PagarCuotaUseCase pagarCuotaUseCase;

	public CuotaController(ObtenerCuotasUseCase obtenerCuotasUseCase, PagarCuotaUseCase pagarCuotaUseCase) {
		this.obtenerCuotasUseCase = obtenerCuotasUseCase;
		this.pagarCuotaUseCase = pagarCuotaUseCase;
	}

    @GetMapping("/consultar/{idPrestamo}")
    public ResponseEntity<List<Cuota>> obtenerCuotas(@PathVariable Integer idPrestamo){
        return ResponseEntity.ok(obtenerCuotasUseCase.obtenerCuotas(idPrestamo));
    }

    @PostMapping("/pagar/{idCuota}")
    public ResponseEntity<?> pagarCuota(@PathVariable Integer idCuota, @RequestBody PagarCuotaRequestDto datos){
        pagarCuotaUseCase.pagarCuota(idCuota, datos);
        return ResponseEntity.ok(Map.of("Mensaje", "Se pago la cuota de forma exitosa."));
    }
}
