package com.HR.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.HR.persistence.entities.Contract}
 */
@Data
public class ContractDto implements Serializable {
    Integer id;
    @NotNull
    Integer employeeID;
    @NotNull
    @Size(max = 255)
    String contractType;
    @NotNull
    LocalDate startDate;
    LocalDate endDate;
    BigDecimal salary;
    Integer hoursPerWeek;
    String terms;
}