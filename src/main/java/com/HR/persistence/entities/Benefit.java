package com.HR.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "benefits")
public class Benefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BenefitID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "BenefitName", nullable = false)
    private String benefitName;

    @Lob
    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "benefit")
    private Set<EmployeeBenefit> employeeBenefits = new LinkedHashSet<>();

}