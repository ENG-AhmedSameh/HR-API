package com.HR.dto;

import com.HR.controllers.soap.adapters.LocalDateAdapter;
import com.HR.persistence.entities.entitiesIDs.EmployeebenefitId;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.HR.persistence.entities.EmployeeBenefit}
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeBenefitDto implements Serializable {
    EmployeebenefitId id;
    EmployeeDto employee;
    BenefitDto benefit;
    @JsonbDateFormat("yyyy-MM-dd")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    LocalDate enrollmentDate;


}