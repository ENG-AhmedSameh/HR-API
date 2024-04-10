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
import java.time.LocalDate;

/**
 * DTO for {@link com.HR.persistence.entities.Vacation}
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VacationDto implements Serializable {
    Integer id;
    Integer employeeID;
    @JsonbDateFormat("yyyy-MM-dd")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    LocalDate startDate;
    @JsonbDateFormat("yyyy-MM-dd")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    LocalDate endDate;
    @Size(max = 50)
    String status;
}