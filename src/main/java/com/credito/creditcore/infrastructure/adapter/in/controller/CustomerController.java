package com.credito.creditcore.infrastructure.adapter.in.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.customer.port.GetCustomerUseCase;
import com.credito.creditcore.application.customer.port.RegisterCustomerUseCase;
import com.credito.creditcore.application.dto.customer.CustomerDto;
import com.credito.creditcore.application.dto.customer.CustomerSalaryDto;
import com.credito.creditcore.infrastructure.adapter.in.mapper.CustomerMapperIn;

@RestController
@RequestMapping("/api/credito/customers")
public class CustomerController {

    private final RegisterCustomerUseCase registerCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;

    public CustomerController(RegisterCustomerUseCase registerCustomerUseCase, GetCustomerUseCase getCustomerUseCase) {
        this.registerCustomerUseCase = registerCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable Integer customerId) {
        CustomerDto customerDto = CustomerMapperIn.createDto(getCustomerUseCase.getCustomer(customerId));
        
        return ResponseEntity.ok(customerDto);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer customerId, @RequestBody CustomerSalaryDto request) {

        return ResponseEntity.ok(null);
    }

    @PostMapping("/{personId}")
    public ResponseEntity<?> registerCustomer(@PathVariable Integer personId, @RequestBody CustomerSalaryDto request) {

        registerCustomerUseCase.registerCustomer(request.salary(), personId);

        return ResponseEntity.ok(Map.of("message", "Customer registered successfully."));
    }
}
