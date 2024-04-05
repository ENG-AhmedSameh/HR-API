package com.HR.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContractID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EmployeeID", nullable = false)
    private Employee employee;

    @Size(max = 255)
    @NotNull
    @Column(name = "ContractType", nullable = false)
    private String contractType;

    @NotNull
    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @Column(name = "Salary", precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(name = "HoursPerWeek")
    private Integer hoursPerWeek;

    @Lob
    @Column(name = "Terms")
    private String terms;

}