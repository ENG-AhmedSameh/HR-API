package com.HR.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.HR.persistence.entities.Vacation}
 */
@Data
public class VacationDto implements Serializable {
    Integer id;
    @NotNull
    Integer employeeID;
    @NotNull
    LocalDate startDate;
    @NotNull
    LocalDate endDate;
    @NotNull
    @Size(max = 50)
    String status;
}