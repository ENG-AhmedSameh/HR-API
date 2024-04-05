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
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TeamID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "TeamName", nullable = false)
    private String teamName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DepartmentID", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TeamLeaderID")
    private Employee teamLeader;

    @OneToMany(mappedBy = "team")
    private Set<Employee> employees = new LinkedHashSet<>();

}