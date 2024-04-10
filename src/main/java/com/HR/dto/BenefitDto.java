package com.HR.dto;

import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.HR.persistence.entities.Benefit}
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BenefitDto implements Serializable {
    Integer id;
    @Size(max = 255)
    String benefitName;
    String description;
}