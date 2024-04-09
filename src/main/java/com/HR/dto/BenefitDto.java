package com.HR.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.HR.persistence.entities.Benefit}
 */
@Value
public class BenefitDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String benefitName;
    String description;

//    Set<EmployeeBenefitDto> employeeBenefits;
}