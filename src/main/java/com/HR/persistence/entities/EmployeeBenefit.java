package com.HR.persistence.entities;

import com.HR.persistence.entities.entitiesIDs.EmployeebenefitId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "employeebenefits")
public class EmployeeBenefit {
    @EmbeddedId
    private EmployeebenefitId id;

    @MapsId("employeeID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EmployeeID", nullable = false)
    private Employee employee;

    @MapsId("benefitID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BenefitID", nullable = false)
    private Benefit benefit;

    @Column(name = "EnrollmentDate")
    private LocalDate enrollmentDate;

    public EmployeeBenefit(Employee employee, Benefit benefit, LocalDate enrollmentDate) {
        this.employee = employee;
        this.benefit = benefit;
        this.enrollmentDate = enrollmentDate;
        this.id = new EmployeebenefitId(employee.getId(), benefit.getId());
    }

    public EmployeeBenefit() {

    }
}