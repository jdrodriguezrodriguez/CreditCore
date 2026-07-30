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

import com.credito.creditcore.application.dto.installment.InstallmentResponseDto;
import com.credito.creditcore.application.dto.installment.PayInstallmentRequestDto;
import com.credito.creditcore.application.dto.installment.PayLateFeeRequestDto;
import com.credito.creditcore.application.installment.port.GetInstallmentUseCase;
import com.credito.creditcore.application.installment.port.GetInstallmentsUseCase;
import com.credito.creditcore.application.installment.port.PayInstallmentUseCase;
import com.credito.creditcore.application.installment.port.ProcessLateFeeUseCase;
import com.credito.creditcore.domain.model.Installment;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/credito/installments")
public class InstallmentController {

    private final GetInstallmentsUseCase getInstallmentsUseCase;
    private final GetInstallmentUseCase getInstallmentUseCase;
    private final PayInstallmentUseCase payInstallmentUseCase;
    private final ProcessLateFeeUseCase processLateFeeUseCase;

    public InstallmentController(GetInstallmentsUseCase getInstallmentsUseCase,
            GetInstallmentUseCase getInstallmentUseCase, PayInstallmentUseCase payInstallmentUseCase,
            ProcessLateFeeUseCase processLateFeeUseCase) {
        this.getInstallmentsUseCase = getInstallmentsUseCase;
        this.getInstallmentUseCase = getInstallmentUseCase;
        this.payInstallmentUseCase = payInstallmentUseCase;
        this.processLateFeeUseCase = processLateFeeUseCase;
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<List<InstallmentResponseDto>> getInstallments(@PathVariable Integer loanId) {
        return ResponseEntity.ok(getInstallmentsUseCase.getInstallments(loanId));
    }

    @GetMapping("/installment/{installmentId}")
    public ResponseEntity<?> getInstallment(@PathVariable Integer installmentId) {
        return ResponseEntity.ok(getInstallmentUseCase.getInstallment(installmentId));
    }

    @PostMapping("/pay/{loanId}")
    public ResponseEntity<?> payInstallments(@PathVariable Integer loanId,
            @RequestBody PayInstallmentRequestDto request) {
        payInstallmentUseCase.payInstallment(loanId, request);
        return ResponseEntity.ok(Map.of("message", "Installment paid successfully."));
    }
    
    @PutMapping("/pay/{loanId}/LateFee")
    public ResponseEntity<?> PayLateFee(@PathVariable Integer loanId, @RequestBody PayLateFeeRequestDto request) {
        processLateFeeUseCase.processLateFee(loanId, request);
        return ResponseEntity.ok(Map.of("message","Late fee paid successfully."));
    }
}
