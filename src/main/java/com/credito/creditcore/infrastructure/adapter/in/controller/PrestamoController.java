package com.credito.creditcore.infrastructure.adapter.in.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.dto.prestamo.LoanCreationRequestDto;
import com.credito.creditcore.application.dto.prestamo.LoanSimulationRequestDto;
import com.credito.creditcore.application.loan.port.ActivateLoanUseCase;
import com.credito.creditcore.application.loan.port.CreateLoanUseCase;
import com.credito.creditcore.application.loan.port.GetLoanUseCase;
import com.credito.creditcore.application.loan.port.SimulateLoanUseCase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/credito/prestamo")
public class PrestamoController {

    private final SimulateLoanUseCase simulateLoanUseCase;
    private final CreateLoanUseCase createLoanUseCase;
    private final ActivateLoanUseCase activateLoanUseCase;
    private final GetLoanUseCase getLoanUseCase;

    public PrestamoController(SimulateLoanUseCase simulateLoanUseCase,
            CreateLoanUseCase createLoanUseCase, ActivateLoanUseCase activateLoanUseCase,
            GetLoanUseCase getLoanUseCase) {
        this.simulateLoanUseCase = simulateLoanUseCase;
        this.createLoanUseCase = createLoanUseCase;
        this.activateLoanUseCase = activateLoanUseCase;
        this.getLoanUseCase = getLoanUseCase;
    }

    @GetMapping("/{personId}")
    public ResponseEntity<?> getLoan(@PathVariable int personId) {
        return ResponseEntity.ok(getLoanUseCase.getLoan(personId));
    }

    @PostMapping("/simulate/{personId}")
    public ResponseEntity<?> simulateLoan(@PathVariable int personId,
            @RequestBody LoanSimulationRequestDto request) {
        return ResponseEntity.ok(simulateLoanUseCase.simulateLoan(request, personId));
    }

    @PostMapping("/create/{personId}")
    public ResponseEntity<?> createLoan(@RequestBody LoanCreationRequestDto request, @PathVariable int personId) {
        createLoanUseCase.createLoan(request, personId);
        return ResponseEntity.ok(Map.of("message", "Loan created successfully"));
    }

    @PutMapping("/activate/{personId}")
    public ResponseEntity<?> activateLoan( @PathVariable int personId) {
        activateLoanUseCase.activateLoan(personId);
        return ResponseEntity.ok(Map.of("Mensaje", "Loan activated successfully"));
    }

}
