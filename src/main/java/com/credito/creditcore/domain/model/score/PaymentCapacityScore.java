package com.credito.creditcore.domain.model.score;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class PaymentCapacityScore {

    private final BigDecimal salary;
    private final int score;

    public PaymentCapacityScore(BigDecimal salary, int score) {
        this.salary = salary;
        this.score = score;
    }
}