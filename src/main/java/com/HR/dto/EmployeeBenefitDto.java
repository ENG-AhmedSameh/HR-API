package com.HR.dto;

import com.HR.persistence.entities.Employee;
import com.HR.persistence.entities.entitiesIDs.EmployeebenefitId;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.HR.persistence.entities.EmployeeBenefit}
 */
@Data
public class EmployeeBenefitDto implements Serializable {
    EmployeebenefitId id;
    EmployeeDto employee;
    BenefitDto benefit;
    LocalDate enrollmentDate;


}