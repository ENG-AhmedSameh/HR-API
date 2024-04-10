package com.HR.dto;

import com.HR.controllers.soap.adapters.LocalDateAdapter;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.HR.persistence.entities.Contract}
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ContractDto implements Serializable {
    Integer id;
    Integer employeeID;
    @Size(max = 255)
    String contractType;
    @JsonbDateFormat("yyyy-MM-dd")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    LocalDate startDate;
    @JsonbDateFormat("yyyy-MM-dd")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    LocalDate endDate;
    BigDecimal salary;
    Integer hoursPerWeek;
    String terms;
}