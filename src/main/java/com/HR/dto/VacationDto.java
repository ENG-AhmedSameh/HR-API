package com.HR.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
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
    Integer employeeID;
    @JsonbDateFormat("yyyy-MM-dd")
    LocalDate startDate;
    @JsonbDateFormat("yyyy-MM-dd")
    LocalDate endDate;
    @Size(max = 50)
    String status;
}