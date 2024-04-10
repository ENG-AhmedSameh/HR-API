package com.HR.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.HR.persistence.entities.Contract}
 */
@Data
public class ContractDto implements Serializable {
    Integer id;
    Integer employeeID;
    @Size(max = 255)
    String contractType;
    @JsonbDateFormat("yyyy-MM-dd")
    LocalDate startDate;
    @JsonbDateFormat("yyyy-MM-dd")
    LocalDate endDate;
    BigDecimal salary;
    Integer hoursPerWeek;
    String terms;
}