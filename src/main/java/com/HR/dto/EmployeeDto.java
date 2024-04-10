package com.HR.dto;

import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.HR.persistence.entities.Employee}
 */
//@Value
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeDto implements Serializable {
    Integer id;
    @Size(max = 255)
    String firstName;
    @Size(max = 255)
    String lastName;
    Integer directManagerID;
    String directManagerName;
    Integer teamID;
    String teamName;
    Integer totalVacationDaysAllotted;
}