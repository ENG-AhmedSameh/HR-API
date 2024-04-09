package com.HR.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.HR.persistence.entities.Department}
 */
@Value
public class DepartmentDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String departmentName;
    EmployeeDto manager;
//    Set<EmployeeDto> employees;
//    Set<TeamDto> teams;
}