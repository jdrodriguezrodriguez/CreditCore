package com.credito.creditcore.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
    private PersonEntity person;

     @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Column(name = "credit_history_score", nullable = false)
    private Integer creditHistoryScore;

    @Column(name = "registration_date", nullable = false)
    private LocalDate  registrationDate;

    public CustomerEntity(){}

    public CustomerEntity(PersonEntity person, BigDecimal salary, Integer creditHistoryScore,
            LocalDate registrationDate) {
        this.person = person;
        this.salary = salary;
        this.creditHistoryScore = creditHistoryScore;
        this.registrationDate = registrationDate;
    }
}
