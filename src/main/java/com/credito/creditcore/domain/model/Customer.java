package com.credito.creditcore.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class Customer {

    private Integer customerId;
    private Person person;
    private BigDecimal salary;
    private Integer creditHistoryScore;
    private LocalDate registrationDate;

    public Customer() {
    }

    public Customer(
            Integer customerId,
            Person person,
            BigDecimal salary,
            Integer creditHistoryScore,
            LocalDate registrationDate) {

        if (person == null) {
            throw new IllegalArgumentException("Person is required.");
        }

        if (salary == null || creditHistoryScore == null) {
            throw new IllegalArgumentException("Financial data is required.");
        }

        this.customerId = customerId;
        this.person = person;
        this.salary = salary;
        this.creditHistoryScore = creditHistoryScore;
        this.registrationDate = registrationDate;
    }

    public static Customer create(
            BigDecimal salary, Integer creditScore, Person person) {

        return new Customer(
                null,
                person,
                salary,
                creditScore,
                LocalDate.now());
    }
}