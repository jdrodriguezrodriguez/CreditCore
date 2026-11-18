package com.credito.creditcore.domain.model.score;

import lombok.Getter;

@Getter
public class ScoreIncrease {

    private final Integer countLate;
    private final Integer score;

    public ScoreIncrease(Integer countLate, Integer score) {
        this.countLate = countLate;
        this.score = score;
    }
}
