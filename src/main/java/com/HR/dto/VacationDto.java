package com.HR.dto;

import com.HR.persistence.entities.Employee;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.HR.persistence.entities.Vacation}
 */
@Value
public class VacationDto implements Serializable {
    Integer id;
    @NotNull
    EmployeeDto employee;
    @NotNull
    LocalDate startDate;
    @NotNull
    LocalDate endDate;
    @NotNull
    @Size(max = 50)
    String status;
}