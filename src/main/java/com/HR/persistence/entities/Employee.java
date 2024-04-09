package com.HR.persistence.entities;

import jakarta.annotation.Nullable;
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
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Size(max = 255)
    @NotNull
    @Column(name = "LastName", nullable = false)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentID")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DirectManagerID")
    @Nullable
    private Employee directManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TeamID")
    private Team team;

    @Column(name = "TotalVacationDaysAllotted")
    private Integer totalVacationDaysAllotted;

    @OneToMany(mappedBy = "employee")
    private Set<Contract> contracts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "manager")
    private Set<Department> departments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<EmployeeBenefit> employeeBenefits = new LinkedHashSet<>();

    @OneToMany(mappedBy = "directManager")
    private Set<Employee> managedEmployees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "teamLeader")
    private Set<Team> leadedTeams = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<Vacation> vacations = new LinkedHashSet<>();

    public void addContract(Contract contract) {
        contracts.add(contract);
        contract.setEmployee(this);
    }

    public void removeContract(Contract contract) {
        contracts.remove(contract);
        contract.setEmployee(null);
    }

    public void addEmployeeBenefit(EmployeeBenefit employeeBenefit) {
        employeeBenefits.add(employeeBenefit);
        employeeBenefit.setEmployee(this);
    }

}