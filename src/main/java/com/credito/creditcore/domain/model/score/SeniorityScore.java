package com.credito.creditcore.domain.model.score;

import lombok.Getter;

@Getter
public class SeniorityScore {

    private final long seniority;
    private final int score;

    public SeniorityScore(long seniority, int score) {
        this.seniority = seniority;
        this.score = score;
    }
}