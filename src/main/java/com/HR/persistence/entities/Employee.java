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
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID", nullable = false)
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
    @JoinColumn(name = "DirectManager")
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
    private Set<EmployeeBenefit> employeebenefits = new LinkedHashSet<>();

    @OneToMany(mappedBy = "directManager")
    private Set<Employee> employees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "teamLeader")
    private Set<Team> teams = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<Vacation> vacations = new LinkedHashSet<>();

}