package com.HR.dto;

import com.HR.persistence.entities.Employee;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.HR.persistence.entities.Employee}
 */
@Value
public class EmployeeDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String firstName;
    @NotNull
    @Size(max = 255)
    String lastName;
    DepartmentDto department;
    Employee directManager;
    TeamDto team;
    Integer totalVacationDaysAllotted;
    Set<ContractDto> contracts;
    Set<DepartmentDto> departments;
    Set<EmployeeBenefitDto> employeeBenefits;
    Set<Employee> employees;
    Set<TeamDto> teams;
    Set<VacationDto> vacations;
}