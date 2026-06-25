package com.credito.creditcore.application.dto.customer;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.Person;

public record CustomerDto(

    Integer customerId,
    Person person,
    BigDecimal salary,
    Integer score,
    LocalDate dateRecord
    
) {}