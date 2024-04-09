package com.HR.persistence.entities.entitiesIDs;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EmployeebenefitId implements Serializable {
    private static final long serialVersionUID = 3246274879808868614L;
    @NotNull
    @Column(name = "EmployeeID", nullable = false)
    private Integer employeeID;

    @NotNull
    @Column(name = "BenefitID", nullable = false)
    private Integer benefitID;

    public EmployeebenefitId(Integer id, Integer id1) {
        this.employeeID = id;
        this.benefitID = id1;
    }

    public EmployeebenefitId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeebenefitId entity = (EmployeebenefitId) o;
        return Objects.equals(this.employeeID, entity.employeeID) &&
                Objects.equals(this.benefitID, entity.benefitID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, benefitID);
    }

}