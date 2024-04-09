package com.HR.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.HR.persistence.entities.Employee}
 */
//@Value
@Data
public class EmployeeDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String firstName;
    @NotNull
    @Size(max = 255)
    String lastName;
    Integer departmentID;
    @Nullable
    Integer directManagerID;
    Integer teamID;
    Integer totalVacationDaysAllotted;
}