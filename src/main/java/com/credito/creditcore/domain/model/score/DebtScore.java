package com.credito.creditcore.domain.model.score;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class DebtScore {

    private final BigDecimal limit;
    private final int score;

    public DebtScore(BigDecimal limit, int score) {
        this.limit = limit;
        this.score = score;
    }
}