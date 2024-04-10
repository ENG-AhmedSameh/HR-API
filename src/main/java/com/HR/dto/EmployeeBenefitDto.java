package com.HR.dto;

import com.HR.persistence.entities.entitiesIDs.EmployeebenefitId;
import jakarta.json.bind.annotation.JsonbDateFormat;
import lombok.Data;

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
    @JsonbDateFormat("yyyy-MM-dd")
    LocalDate enrollmentDate;


}